// src/main/java/com/mravel/booking/service/RestaurantBookingService.java
package com.mravel.booking.service;

import com.mravel.booking.client.CatalogRestaurantInventoryClient;
import com.mravel.booking.dto.RestaurantBookingDtos.*;
import com.mravel.booking.dto.ResumePaymentDTO;
import com.mravel.booking.model.*;
import com.mravel.booking.payment.PaymentMethodUtils;
import com.mravel.booking.repository.RestaurantBookingRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import java.util.UUID;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class RestaurantBookingService {

  private static final int PENDING_EXPIRE_MINUTES = 30;

  private final RestaurantBookingRepository repo;
  private final CatalogRestaurantInventoryClient invClient;
  private final PaymentAttemptService paymentAttemptService;

  @Transactional
  public RestaurantBookingCreatedDTO create(CreateRestaurantBookingRequest req, String guestSessionId) {
    // 1) validate cơ bản
    if (req == null) throw new IllegalArgumentException("req null");
    if (req.restaurantId() == null) throw new IllegalArgumentException("Thiếu restaurantId");
    if (req.reservationDate() == null || req.reservationTime() == null) throw new IllegalArgumentException("Thiếu thời gian");
    if (req.tables() == null || req.tables().isEmpty()) throw new IllegalArgumentException("Chưa chọn bàn");
    int people = req.people() == null ? 0 : req.people();
    if (people <= 0) throw new IllegalArgumentException("people không hợp lệ");

    // 2) compute deposit (khuyến nghị: BE tự tính từ snapshot catalog, nhưng tạm thời dùng req)
    BigDecimal depositAmount = BigDecimal.ZERO;
    int tablesCount = 0;

    for (SelectedTable t : req.tables()) {
      if (t == null || t.quantity() == null || t.quantity() <= 0) continue;
      if (t.depositPrice() == null) throw new IllegalArgumentException("Thiếu depositPrice cho tableType " + t.tableTypeId());

      BigDecimal line = t.depositPrice().multiply(BigDecimal.valueOf(t.quantity()));
      depositAmount = depositAmount.add(line);
      tablesCount += t.quantity();
    }
    if (tablesCount <= 0) throw new IllegalArgumentException("Số bàn không hợp lệ");
    if (depositAmount.compareTo(BigDecimal.ZERO) <= 0) throw new IllegalArgumentException("depositAmount không hợp lệ");

    // 3) CHECK + HOLD bên catalog
    var checkReq = new CatalogTableReq(req.restaurantId(), req.reservationDate(), req.reservationTime(), req.durationMinutes(),
        req.tables().stream().map(t -> new TableReq(t.tableTypeId(), t.quantity())).toList()
    );
    invClient.check(checkReq);

    var holdReq = new CatalogHoldReq(req.restaurantId(), req.restaurantSlug(), req.reservationDate(), req.reservationTime(), req.durationMinutes(),
        req.tables().stream().map(t -> new TableReq(t.tableTypeId(), t.quantity())).toList()
    );
    invClient.hold(holdReq);

    // 4) tạo booking record
    String code = "RB-" + UUID.randomUUID().toString().replace("-", "").substring(0, 10).toUpperCase();

    RestaurantBooking booking = RestaurantBooking.builder()
        .code(code)
        .userId(req.userId())
        .guestSessionId(req.userId() == null ? guestSessionId : null)

        .contactName(req.contactName())
        .contactPhone(req.contactPhone())
        .contactEmail(req.contactEmail())
        .note(req.note())

        .restaurantId(req.restaurantId())
        .restaurantSlug(req.restaurantSlug())
        .restaurantName(req.restaurantName())

        .reservationDate(req.reservationDate())
        .reservationTime(req.reservationTime())
        .durationMinutes(req.durationMinutes())
        .people(people)
        .tablesCount(tablesCount)

        .payOption(BookingBase.PayOption.DEPOSIT)
        .totalAmount(depositAmount)          // nhà hàng: total = deposit
        .depositAmount(depositAmount)
        .amountPayable(depositAmount)
        .amountPaid(BigDecimal.ZERO)
        .currencyCode("VND")

        .status(BookingBase.BookingStatus.PENDING_PAYMENT)
        .paymentStatus(BookingBase.PaymentStatus.PENDING)
        .inventoryDeducted(true)             // nghĩa là đang HELD
        .build();

    List<BookingTable> lines = req.tables().stream()
        .filter(t -> t != null && t.quantity() != null && t.quantity() > 0)
        .map(t -> {
            BookingTable bt = BookingTable.builder()
                .booking(booking)
                .tableTypeId(t.tableTypeId())
                .tableTypeName(t.tableTypeName())
                .seats(t.seats())
                .quantity(t.quantity())
                .depositPrice(t.depositPrice())
                .totalDeposit(t.depositPrice().multiply(BigDecimal.valueOf(t.quantity())))
                .build();
            return bt;
        })
        .collect(Collectors.toList());

    booking.setTables(lines);

    repo.save(booking);

    // 5) tạo payUrl (MoMo) - bạn đang dùng orderInfo "dat phong", đổi text cho res
    Instant deadline = booking.getCreatedAt().plus(PENDING_EXPIRE_MINUTES, ChronoUnit.MINUTES);

    Payment.PaymentMethod method = parsePaymentMethod(req.paymentMethod());
    Payment attempt = paymentAttemptService.createOrReusePendingAttempt(booking, method, deadline);

    booking.setPendingPaymentOrderId(attempt.getProviderRequestId());
    booking.setPendingPaymentUrl(attempt.getProviderPayUrl());
    booking.setActivePaymentMethod(attempt.getMethod());
    repo.save(booking);

    return new RestaurantBookingCreatedDTO(
        code,
        booking.getRestaurantName(),
        booking.getRestaurantSlug(),
        booking.getReservationDate(),
        booking.getReservationTime(),
        booking.getDurationMinutes(),
        booking.getPeople(),
        booking.getTablesCount(),
        "DEPOSIT",
        booking.getDepositAmount(),
        booking.getAmountPayable(),
        booking.getCurrencyCode(),
        method.name(),
        attempt.getProviderPayUrl()
    );
  }

  @Transactional
    public void markRestaurantBookingPaidAndConfirm(String bookingCode, Long amount) {
        if (bookingCode == null || bookingCode.isBlank()) {
            throw new IllegalArgumentException("bookingCode không hợp lệ");
        }

        RestaurantBooking b = repo.findByCode(bookingCode)
            .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy booking: " + bookingCode));

        // idempotent: đã success rồi thì thôi
        if (b.getPaymentStatus() == BookingBase.PaymentStatus.SUCCESS) return;

        BigDecimal paid = (amount == null)
            ? (b.getAmountPayable() != null ? b.getAmountPayable() : BigDecimal.ZERO)
            : BigDecimal.valueOf(amount);

        // 1) commit inventory HELD -> BOOKED bên catalog
        try {
            List<TableReq> tables = (b.getTables() == null ? List.<BookingTable>of() : b.getTables()).stream()
                .filter(x -> x != null && x.getQuantity() != null && x.getQuantity() > 0)
                .map(x -> new TableReq(x.getTableTypeId(), x.getQuantity()))
                .collect(Collectors.toList());

            CatalogHoldReq commitReq = new CatalogHoldReq(
                b.getRestaurantId(),
                b.getRestaurantSlug(),
                b.getReservationDate(),
                b.getReservationTime(),
                b.getDurationMinutes(),
                tables
            );

            invClient.commit(commitReq);

            // 2) update booking trạng thái
            b.setAmountPaid(paid);
            b.setPaidAt(Instant.now());
            b.setPaymentStatus(BookingBase.PaymentStatus.SUCCESS);
            b.setStatus(BookingBase.BookingStatus.CONFIRMED); // hoặc PAID rồi CONFIRMED tuỳ bạn
            b.setInventoryDeducted(true);
            b.setPendingPaymentUrl(null);
            b.setPendingPaymentOrderId(null);
            repo.save(b);

        } catch (Exception ex) {
            // payment OK nhưng commit fail => giữ lại trạng thái để admin xử lý (tối thiểu đừng crash lặng lẽ)
            b.setAmountPaid(paid);
            b.setPaidAt(Instant.now());
            b.setPaymentStatus(BookingBase.PaymentStatus.SUCCESS);
            b.setStatus(BookingBase.BookingStatus.PAID);
            b.setPendingPaymentUrl(null);
            b.setPendingPaymentOrderId(null);
            repo.save(b);

            throw ex; // hoặc log + không throw nếu bạn muốn redirect vẫn “êm”
        }
    }

    @Transactional
    public int claimGuestRestaurantBookingsToUser(String sid, Long userId) {
        if (sid == null || sid.isBlank()) return 0;
        if (userId == null) throw new IllegalArgumentException("userId null");

        var items = repo.findByGuestSessionIdOrderByCreatedAtDesc(sid);
        int claimed = 0;

        for (var b : items) {
            // chỉ claim những booking guest (userId null)
            if (b.getUserId() != null) continue;

            b.setUserId(userId);

            // ✅ khuyến nghị: clear guestSessionId để nó biến mất khỏi tab "Đơn trên thiết bị"
            b.setGuestSessionId(null);

            claimed++;
        }

        // JPA dirty checking sẽ tự flush, nhưng saveAll cũng ok:
        // repo.saveAll(items);

        return claimed;
    }

    @Transactional
    public ResumePaymentDTO resumeRestaurantPaymentForOwner(
        String code,
        Long userId,
        String guestSid,
        Payment.PaymentMethod method
    ) {
    var b = repo.findByCode(code.trim())
        .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy booking"));

    // chỉ resume khi còn pending
    if (b.getStatus() != BookingBase.BookingStatus.PENDING_PAYMENT
        || b.getPaymentStatus() != BookingBase.PaymentStatus.PENDING) {
        throw new IllegalStateException("Đơn này không ở trạng thái chờ thanh toán");
    }

    // quyền
    if (b.getUserId() != null) {
        if (userId == null || !userId.equals(b.getUserId())) {
        throw new IllegalStateException("Bạn không có quyền tiếp tục thanh toán booking này");
        }
    } else {
        if (guestSid == null || guestSid.isBlank()
            || b.getGuestSessionId() == null
            || !guestSid.equals(b.getGuestSessionId())) {
        throw new IllegalStateException("Không có quyền tiếp tục thanh toán booking này");
        }
    }

    // trong 30 phút
    if (b.getCreatedAt() == null) throw new IllegalStateException("Booking thiếu createdAt");
    Instant deadline = b.getCreatedAt().plus(PENDING_EXPIRE_MINUTES, ChronoUnit.MINUTES);
    Instant now = Instant.now();
    if (now.isAfter(deadline)) throw new IllegalStateException("Đơn đã quá hạn thanh toán");
    long expiresIn = Duration.between(now, deadline).getSeconds();

    // default method
    Payment.PaymentMethod finalMethod = method;
    if (finalMethod == null) {
        finalMethod = (b.getActivePaymentMethod() != null)
            ? b.getActivePaymentMethod()
            : Payment.PaymentMethod.MOMO_WALLET;
    }

    Payment attempt = paymentAttemptService.createOrReusePendingAttempt(b, finalMethod, deadline);

    b.setPendingPaymentOrderId(attempt.getProviderRequestId());
    b.setPendingPaymentUrl(attempt.getProviderPayUrl());
    b.setActivePaymentMethod(attempt.getMethod());
    repo.save(b);

    return new ResumePaymentDTO(b.getCode(), attempt.getProviderPayUrl(), expiresIn);
    }

    private static final int FREE_CANCEL_MINUTES = 30;

    @Transactional
    public RestaurantBooking cancelRestaurantBooking(String code, Long userId, String guestSid, String reason) {
        // fetch kèm tables để release inventory chắc chắn
        RestaurantBooking b = repo.findWithTablesByCode(code.trim())
            .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy booking"));

        // ✅ quyền: user booking
        if (b.getUserId() != null) {
            if (userId == null || !userId.equals(b.getUserId()))
                throw new IllegalStateException("Bạn không có quyền hủy booking này");
        } else {
            // ✅ quyền: guest booking
            if (guestSid == null || guestSid.isBlank()
                || b.getGuestSessionId() == null
                || !guestSid.equals(b.getGuestSessionId()))
                throw new IllegalStateException("Không có quyền hủy booking này");
        }

        return cancelRestaurantBookingInternal(b, reason);
    }

    @Transactional
    public RestaurantBooking cancelRestaurantBookingByLookup(String code, String reason) {
        RestaurantBooking b = repo.findWithTablesByCode(code.trim())
            .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy booking"));

        // lookup chỉ cho guest
        if (b.getUserId() != null) throw new IllegalStateException("Booking này thuộc tài khoản");

        return cancelRestaurantBookingInternal(b, reason);
    }

    private RestaurantBooking cancelRestaurantBookingInternal(RestaurantBooking b, String reason) {
        // idempotent
        if (b.getStatus() == BookingBase.BookingStatus.CANCELLED
            || b.getStatus() == BookingBase.BookingStatus.CANCELLED_BY_GUEST
            || b.getStatus() == BookingBase.BookingStatus.CANCELLED_BY_PARTNER
            || b.getStatus() == BookingBase.BookingStatus.REFUNDED // legacy
            || b.getStatus() == BookingBase.BookingStatus.COMPLETED) {
        return b;
        }

        if (b.getCreatedAt() == null) throw new IllegalStateException("Booking thiếu createdAt");
        long minutesFromCreate = Duration.between(b.getCreatedAt(), Instant.now()).toMinutes();

        b.setCancelReason(reason);
        b.setCancelledAt(Instant.now());

        // rule hoàn/không hoàn (giống hotel)
        b.setStatus(BookingBase.BookingStatus.CANCELLED_BY_GUEST);

        if (minutesFromCreate <= FREE_CANCEL_MINUTES) {
            b.setPaymentStatus(BookingBase.PaymentStatus.REFUNDED);
        } else {
            b.setPaymentStatus(BookingBase.PaymentStatus.FAILED);
        }

        // clear pending pay info
        b.setPendingPaymentUrl(null);
        b.setPendingPaymentOrderId(null);

        // ✅ release inventory theo đúng client của bạn
        if (Boolean.TRUE.equals(b.getInventoryDeducted())) {
            var tables = (b.getTables() == null ? List.<BookingTable>of() : b.getTables()).stream()
                .filter(x -> x != null && x.getQuantity() != null && x.getQuantity() > 0)
                .map(x -> new TableReq(x.getTableTypeId(), x.getQuantity()))
                .toList();

            // nếu không có tables thì thôi, tránh gọi catalog vô nghĩa
            if (!tables.isEmpty()) {
                CatalogHoldReq releaseReq = new CatalogHoldReq(
                    b.getRestaurantId(),
                    b.getRestaurantSlug(),
                    b.getReservationDate(),
                    b.getReservationTime(),
                    b.getDurationMinutes(),
                    tables
                );

                // oldStatus pending/confirmed/paid đều release (vì bạn đang dùng release để nhả tồn)
                invClient.release(releaseReq);
            }

            b.setInventoryDeducted(false);
        }

        return repo.save(b);
    }

    @Scheduled(fixedDelayString = "${mravel.booking.pending-expire-check-ms:60000}")
    @Transactional
    public void autoCancelPendingRestaurantBookings() {
        Instant cutoff = Instant.now().minus(PENDING_EXPIRE_MINUTES, ChronoUnit.MINUTES);

        var pendings = repo.findPendingsWithTables(
            BookingBase.PaymentStatus.PENDING,
            BookingBase.BookingStatus.PENDING_PAYMENT,
            cutoff
        );

        if (pendings.isEmpty()) return;

        Instant now = Instant.now();

        for (var b : pendings) {
        // double-check idempotent
        if (b.getStatus() != BookingBase.BookingStatus.PENDING_PAYMENT
            || b.getPaymentStatus() != BookingBase.PaymentStatus.PENDING) {
            continue;
        }

        b.setStatus(BookingBase.BookingStatus.CANCELLED);
        b.setPaymentStatus(BookingBase.PaymentStatus.FAILED);
        b.setCancelledAt(now);
        b.setCancelReason("AUTO_CANCEL_NOT_PAID_WITHIN_" + PENDING_EXPIRE_MINUTES + "_MIN");
        b.setPendingPaymentUrl(null);
        b.setPendingPaymentOrderId(null);

        // release HELD tables
        if (Boolean.TRUE.equals(b.getInventoryDeducted())) {
            var tables = (b.getTables() == null ? List.<BookingTable>of() : b.getTables()).stream()
                .filter(x -> x != null && x.getQuantity() != null && x.getQuantity() > 0)
                .map(x -> new TableReq(x.getTableTypeId(), x.getQuantity()))
                .toList();

            CatalogHoldReq releaseReq = new CatalogHoldReq(
                b.getRestaurantId(),
                b.getRestaurantSlug(),
                b.getReservationDate(),
                b.getReservationTime(),
                b.getDurationMinutes(),
                tables
            );

            invClient.release(releaseReq);
            b.setInventoryDeducted(false);
        }
        }

        repo.saveAll(pendings);
    }

    private Payment.PaymentMethod parsePaymentMethod(String raw) {
        return PaymentMethodUtils.parseOrDefault(raw, Payment.PaymentMethod.MOMO_WALLET);
    }

  // ===== DTO nội bộ gửi qua catalog-client (typed thay vì Object) =====
  public record TableReq(String tableTypeId, int quantity) {}
  public record CatalogTableReq(String restaurantId, java.time.LocalDate reservationDate, java.time.LocalTime reservationTime,
                                Integer durationMinutes, List<TableReq> tables) {}
  public record CatalogHoldReq(String restaurantId, String restaurantSlug, java.time.LocalDate reservationDate, java.time.LocalTime reservationTime,
                               Integer durationMinutes, List<TableReq> tables) {}
}
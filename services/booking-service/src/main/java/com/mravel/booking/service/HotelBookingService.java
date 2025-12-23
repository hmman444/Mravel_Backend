// src/main/java/com/mravel/booking/service/HotelBookingService.java
package com.mravel.booking.service;

import com.mravel.booking.client.CatalogInventoryClient;
import com.mravel.booking.client.CatalogInventoryDtos.DeductInventoryRequest;
import com.mravel.booking.client.CatalogInventoryDtos.RollbackInventoryRequest;
import com.mravel.booking.client.CatalogInventoryDtos.RoomRequestDTO;
import com.mravel.booking.dto.HotelBookingDtos.CreateHotelBookingRequest;
import com.mravel.booking.dto.HotelBookingDtos.HotelBookingCreatedDTO;
import com.mravel.booking.dto.HotelBookingDtos.SelectedRoom;
import com.mravel.booking.model.BookingBase.BookingStatus;
import com.mravel.booking.model.BookingBase.PayOption;
import com.mravel.booking.model.BookingBase.PaymentStatus;
import com.mravel.booking.model.BookingRoom;
import com.mravel.booking.model.HotelBooking;
import com.mravel.booking.model.Payment;
import com.mravel.booking.payment.PaymentMethodUtils;
import com.mravel.booking.dto.ResumePaymentDTO;
import com.mravel.booking.repository.HotelBookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HotelBookingService {

    private static final int FREE_CANCEL_MINUTES = 30;
    private static final int PENDING_EXPIRE_MINUTES = 30;
    private static final BigDecimal DEPOSIT_PERCENT = new BigDecimal("0.30");
    private static final String CURRENCY_VND = "VND";

    private final HotelBookingRepository hotelBookingRepository;
    private final CatalogInventoryClient catalogInventoryClient;
    private final PaymentAttemptService paymentAttemptService;

    @Transactional
    public HotelBookingCreatedDTO createHotelBooking(CreateHotelBookingRequest req, String guestSid) {
        validateRequest(req);
        validateDates(req.checkInDate(), req.checkOutDate());

        int nights = (int) ChronoUnit.DAYS.between(req.checkInDate(), req.checkOutDate());
        if (nights <= 0) {
            throw new IllegalArgumentException("Ngày nhận / trả phòng không hợp lệ");
        }

        List<RoomRequestDTO> roomRequests = buildRoomRequestsFromSelectedRooms(req.rooms());
        if (roomRequests.isEmpty()) {
            throw new IllegalArgumentException("Chưa chọn phòng hợp lệ");
        }

        // HOLD tồn kho trước (fail thì rollback transaction -> không tạo booking)
        catalogInventoryClient.holdInventory(new DeductInventoryRequest(
                req.hotelId(),
                req.hotelSlug(),
                req.checkInDate(),
                req.checkOutDate(),
                roomRequests
        ));

        // Build rooms + tính tiền
        List<BookingRoom> roomEntities = new ArrayList<>();
        BigDecimal totalAmount = BigDecimal.ZERO;
        int totalRoomsCount = 0;

        for (SelectedRoom sr : req.rooms()) {
            Integer qty = sr.quantity();
            if (qty == null || qty <= 0) continue;

            if (sr.pricePerNight() == null) {
                throw new IllegalArgumentException("Thiếu giá/đêm cho phòng " + sr.roomTypeId());
            }

            BigDecimal lineTotal = sr.pricePerNight()
                    .multiply(BigDecimal.valueOf(nights))
                    .multiply(BigDecimal.valueOf(qty));

            totalAmount = totalAmount.add(lineTotal);
            totalRoomsCount += qty;

            BookingRoom br = BookingRoom.builder()
                    .roomTypeId(sr.roomTypeId())
                    .roomTypeName(sr.roomTypeName())
                    .ratePlanId(sr.ratePlanId())
                    .ratePlanName(sr.ratePlanName())
                    .quantity(qty)
                    .nights(nights)
                    .pricePerNight(sr.pricePerNight())
                    .totalAmount(lineTotal)
                    .build();

            roomEntities.add(br);
        }

        if (totalRoomsCount <= 0 || totalAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Tổng tiền booking phải lớn hơn 0");
        }

        PayOption payOption = parsePayOption(req.payOption());
        BigDecimal depositAmount = calculateDeposit(totalAmount, payOption);
        BigDecimal amountPayable = (payOption == PayOption.FULL) ? totalAmount : depositAmount;

        HotelBooking booking = HotelBooking.builder()
                .code(generateCode())
                .userId(req.userId())
                .guestSessionId(req.userId() == null ? guestSid : null)
                .contactName(req.contactName())
                .contactPhone(req.contactPhone())
                .contactEmail(req.contactEmail())
                .note(req.note())
                .hotelId(req.hotelId())
                .hotelSlug(req.hotelSlug())
                .hotelName(req.hotelName())
                .checkInDate(req.checkInDate())
                .checkOutDate(req.checkOutDate())
                .nights(nights)
                .roomsCount(totalRoomsCount)
                .payOption(payOption)
                .totalAmount(totalAmount)
                .depositAmount(depositAmount)
                .amountPayable(amountPayable)
                .amountPaid(BigDecimal.ZERO)
                .currencyCode(CURRENCY_VND)
                .status(BookingStatus.PENDING_PAYMENT)
                .paymentStatus(PaymentStatus.PENDING)
                .inventoryDeducted(true)
                .build();

        roomEntities.forEach(br -> br.setBooking(booking));
        booking.setRooms(roomEntities);

        HotelBooking saved = hotelBookingRepository.save(booking);

        Instant deadline = saved.getCreatedAt().plus(PENDING_EXPIRE_MINUTES, ChronoUnit.MINUTES);

        Payment.PaymentMethod method = parsePaymentMethod(req.paymentMethod());

        Payment attempt = paymentAttemptService.createOrReusePendingAttempt(saved, method, deadline);

        saved.setPendingPaymentOrderId(attempt.getProviderRequestId());
        saved.setPendingPaymentUrl(attempt.getProviderPayUrl());
        saved.setActivePaymentMethod(attempt.getMethod());
        hotelBookingRepository.save(saved);

        return HotelBookingMapper.toCreatedDTO(saved, method.name(), attempt.getProviderPayUrl());
    }

    @Transactional
    public ResumePaymentDTO resumeHotelPaymentForOwner(String code, Long userId, String guestSid, Payment.PaymentMethod method){
        HotelBooking b = hotelBookingRepository.findByCode(code.trim())
            .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy booking"));

        // ✅ chỉ cho resume khi còn pending
        if (b.getStatus() != BookingStatus.PENDING_PAYMENT || b.getPaymentStatus() != PaymentStatus.PENDING) {
            throw new IllegalStateException("Đơn này không ở trạng thái chờ thanh toán");
        }

        // ✅ quyền: nếu booking thuộc account => cần userId đúng
        if (b.getUserId() != null) {
            if (userId == null || !userId.equals(b.getUserId())) {
                throw new IllegalStateException("Bạn không có quyền tiếp tục thanh toán booking này");
            }
        } else {
            // booking guest => cần guestSid đúng
            if (guestSid == null || guestSid.isBlank() || b.getGuestSessionId() == null || !guestSid.equals(b.getGuestSessionId())) {
                throw new IllegalStateException("Không có quyền tiếp tục thanh toán booking này");
            }
        }

        // ✅ trong 30 phút
        Instant deadline = b.getCreatedAt().plus(PENDING_EXPIRE_MINUTES, ChronoUnit.MINUTES);
        Instant now = Instant.now();
        if (now.isAfter(deadline)) throw new IllegalStateException("Đơn đã quá hạn thanh toán");
        long expiresIn = Duration.between(now, deadline).getSeconds();

        // default method: nếu FE không gửi thì dùng method gần nhất, không có thì MOMO
        Payment.PaymentMethod finalMethod = method;
        if (finalMethod == null) {
        finalMethod = (b.getActivePaymentMethod() != null) ? b.getActivePaymentMethod() : Payment.PaymentMethod.MOMO_WALLET;
        }

        Payment attempt = paymentAttemptService.createOrReusePendingAttempt(b, finalMethod, deadline);

        // update active attempt on booking
        b.setPendingPaymentOrderId(attempt.getProviderRequestId());
        b.setPendingPaymentUrl(attempt.getProviderPayUrl());
        b.setActivePaymentMethod(attempt.getMethod());

        hotelBookingRepository.save(b);

        return new ResumePaymentDTO(b.getCode(), attempt.getProviderPayUrl(), expiresIn);
    }

    @Transactional
    public HotelBooking markHotelBookingPaidAndConfirm(String bookingCode, BigDecimal paidAmount) {
        HotelBooking booking = hotelBookingRepository.findByCode(bookingCode)
                .orElseThrow(() -> new IllegalArgumentException("Booking không tồn tại"));

        // Idempotent
        if (booking.getStatus() != BookingStatus.PENDING_PAYMENT) {
            return booking;
        }

        // COMMIT tồn kho: HOLD -> BOOKED
        if (Boolean.TRUE.equals(booking.getInventoryDeducted())) {
            List<RoomRequestDTO> roomRequests = buildRoomRequestsFromBooking(booking);
            catalogInventoryClient.commitInventory(new DeductInventoryRequest(
                    booking.getHotelId(),
                    booking.getHotelSlug(),
                    booking.getCheckInDate(),
                    booking.getCheckOutDate(),
                    roomRequests
            ));
        }

        BigDecimal finalPaid = (paidAmount != null && paidAmount.compareTo(BigDecimal.ZERO) > 0)
                ? paidAmount
                : booking.getAmountPayable();

        booking.setAmountPaid(finalPaid);
        booking.setPaidAt(Instant.now());
        booking.setPaymentStatus(PaymentStatus.SUCCESS);
        booking.setStatus(BookingStatus.CONFIRMED);
        booking.setPendingPaymentUrl(null);
        booking.setPendingPaymentOrderId(null);

        return hotelBookingRepository.save(booking);
    }

    @Transactional
    public HotelBooking cancelHotelBooking(String bookingCode, Long userId, String guestSid, String reason) {
    HotelBooking booking = hotelBookingRepository.findByCode(bookingCode.trim())
        .orElseThrow(() -> new IllegalArgumentException("Booking không tồn tại"));

    // ✅ quyền: user booking
    if (booking.getUserId() != null) {
        if (userId == null || !userId.equals(booking.getUserId()))
        throw new IllegalStateException("Bạn không có quyền hủy booking này");
    } else {
        // ✅ quyền: guest booking
        if (guestSid == null || guestSid.isBlank()
            || booking.getGuestSessionId() == null
            || !guestSid.equals(booking.getGuestSessionId()))
        throw new IllegalStateException("Không có quyền hủy booking này");
    }

    return cancelHotelBookingInternal(booking, reason);
    }

    @Transactional
    public HotelBooking cancelHotelBookingByLookup(String bookingCode, String reason) {
    HotelBooking booking = hotelBookingRepository.findByCode(bookingCode.trim())
        .orElseThrow(() -> new IllegalArgumentException("Booking không tồn tại"));

    // lookup chỉ cho guest
    if (booking.getUserId() != null) throw new IllegalStateException("Booking này thuộc tài khoản");

    return cancelHotelBookingInternal(booking, reason);
    }

    private HotelBooking cancelHotelBookingInternal(HotelBooking booking, String reason) {
        if (booking.getStatus() == BookingStatus.CANCELLED
            || booking.getStatus() == BookingStatus.CANCELLED_BY_GUEST
            || booking.getStatus() == BookingStatus.CANCELLED_BY_PARTNER
            || booking.getStatus() == BookingStatus.REFUNDED
            || booking.getStatus() == BookingStatus.COMPLETED) {
        return booking;
    }

    BookingStatus oldStatus = booking.getStatus();
    long minutesFromCreate = Duration.between(booking.getCreatedAt(), Instant.now()).toMinutes();

    booking.setCancelReason(reason);
    booking.setCancelledAt(Instant.now());

    // ✅ status nói “ai huỷ”
    booking.setStatus(BookingStatus.CANCELLED_BY_GUEST);

    // ✅ paymentStatus nói “hoàn tiền hay không”
    boolean shouldRefund;
    if (minutesFromCreate <= FREE_CANCEL_MINUTES) {
        shouldRefund = true;
    } else {
        // sau 30p: DEPOSIT không hoàn, FULL hoàn (đúng rule bạn đang dùng)
        shouldRefund = booking.getPayOption() == PayOption.FULL;
    }

    if (shouldRefund) {
        booking.setPaymentStatus(PaymentStatus.REFUNDED);
    } else {
        booking.setPaymentStatus(PaymentStatus.FAILED);
    }

    // inventory rollback/release giữ nguyên như bạn viết
    if (Boolean.TRUE.equals(booking.getInventoryDeducted())) {
        List<RoomRequestDTO> roomRequests = buildRoomRequestsFromBooking(booking);

        if (oldStatus == BookingStatus.PENDING_PAYMENT) {
        catalogInventoryClient.releaseHold(new RollbackInventoryRequest(
            booking.getHotelId(),
            booking.getCheckInDate(),
            booking.getCheckOutDate(),
            roomRequests
        ));
        booking.setInventoryDeducted(false);
        } else if (oldStatus == BookingStatus.CONFIRMED || oldStatus == BookingStatus.PAID) {
        catalogInventoryClient.rollbackInventory(new RollbackInventoryRequest(
            booking.getHotelId(),
            booking.getCheckInDate(),
            booking.getCheckOutDate(),
            roomRequests
        ));
        booking.setInventoryDeducted(false);
        }
    }

    booking.setPendingPaymentUrl(null);
    booking.setPendingPaymentOrderId(null);

    return hotelBookingRepository.save(booking);
    }

    @Scheduled(fixedDelayString = "${mravel.booking.pending-expire-check-ms:60000}")
    @Transactional
    public void autoCancelPendingHotelBookings() {
        Instant cutoff = Instant.now().minus(PENDING_EXPIRE_MINUTES, ChronoUnit.MINUTES);

        List<HotelBooking> pendings =
                hotelBookingRepository.findByStatusAndCreatedAtBefore(BookingStatus.PENDING_PAYMENT, cutoff);

        if (pendings.isEmpty()) return;

        Instant now = Instant.now();

        for (HotelBooking b : pendings) {
            if (b.getStatus() != BookingStatus.PENDING_PAYMENT) continue;

            b.setStatus(BookingStatus.CANCELLED);
            b.setPaymentStatus(PaymentStatus.FAILED);
            b.setCancelledAt(now);
            b.setCancelReason("AUTO_CANCEL_NOT_PAID_WITHIN_" + PENDING_EXPIRE_MINUTES + "_MIN");
            b.setPendingPaymentUrl(null);
            b.setPendingPaymentOrderId(null);

            if (Boolean.TRUE.equals(b.getInventoryDeducted())) {
                List<RoomRequestDTO> roomRequests = buildRoomRequestsFromBooking(b);
                catalogInventoryClient.releaseHold(new RollbackInventoryRequest(
                        b.getHotelId(),
                        b.getCheckInDate(),
                        b.getCheckOutDate(),
                        roomRequests
                ));
                b.setInventoryDeducted(false);
            }
        }

        hotelBookingRepository.saveAll(pendings);
    }

    // ========================= Helpers =========================

    private Payment.PaymentMethod parsePaymentMethod(String raw) {
        return PaymentMethodUtils.parseOrDefault(raw, Payment.PaymentMethod.MOMO_WALLET);
    }

    private void validateRequest(CreateHotelBookingRequest req) {
        if (req == null) throw new IllegalArgumentException("Request không được null");

        if (req.contactName() == null || req.contactName().isBlank()) {
            throw new IllegalArgumentException("contactName không được trống");
        }
        if (req.contactPhone() == null || req.contactPhone().isBlank()) {
            throw new IllegalArgumentException("contactPhone không được trống");
        }
        if (req.hotelId() == null || req.hotelId().isBlank()
                || req.hotelSlug() == null || req.hotelSlug().isBlank()) {
            throw new IllegalArgumentException("Thiếu thông tin khách sạn");
        }
        if (req.checkInDate() == null || req.checkOutDate() == null) {
            throw new IllegalArgumentException("Thiếu ngày nhận/trả phòng");
        }
        if (req.rooms() == null || req.rooms().isEmpty()) {
            throw new IllegalArgumentException("Chưa chọn phòng");
        }
        if (req.payOption() == null || req.payOption().isBlank()) {
            throw new IllegalArgumentException("Thiếu payOption (FULL/DEPOSIT)");
        }
    }

    private void validateDates(LocalDate checkIn, LocalDate checkOut) {
        LocalDate today = LocalDate.now();

        if (checkIn.isBefore(today)) {
            throw new IllegalArgumentException("Ngày nhận phòng phải từ hôm nay trở đi");
        }
        if (!checkOut.isAfter(checkIn)) {
            throw new IllegalArgumentException("Ngày trả phòng phải sau ngày nhận phòng");
        }
    }

    private PayOption parsePayOption(String raw) {
        try {
            return PayOption.valueOf(raw.trim().toUpperCase());
        } catch (Exception e) {
            throw new IllegalArgumentException("payOption không hợp lệ (FULL/DEPOSIT)");
        }
    }

    private BigDecimal calculateDeposit(BigDecimal totalAmount, PayOption payOption) {
        if (payOption == PayOption.FULL) return totalAmount;
        return totalAmount.multiply(DEPOSIT_PERCENT);
    }

    private String generateCode() {
        String random = UUID.randomUUID().toString().replace("-", "")
                .substring(0, 8)
                .toUpperCase();
        return "BK-" + random;
    }

    private List<RoomRequestDTO> buildRoomRequestsFromSelectedRooms(List<SelectedRoom> rooms) {
        if (rooms == null || rooms.isEmpty()) return List.of();

        Map<String, Integer> map = new HashMap<>();

        for (SelectedRoom sr : rooms) {
            int qty = (sr.quantity() == null) ? 0 : sr.quantity();
            if (qty <= 0) continue;

            String roomTypeId = sr.roomTypeId();
            if (roomTypeId == null || roomTypeId.isBlank()) continue;

            map.put(roomTypeId, map.getOrDefault(roomTypeId, 0) + qty);
        }

        return map.entrySet().stream()
                .map(e -> new RoomRequestDTO(e.getKey(), e.getValue()))
                .toList();
    }

    private List<RoomRequestDTO> buildRoomRequestsFromBooking(HotelBooking booking) {
        if (booking.getRooms() == null || booking.getRooms().isEmpty()) return List.of();

        Map<String, Integer> map = new HashMap<>();

        for (BookingRoom br : booking.getRooms()) {
            int qty = (br.getQuantity() == null) ? 0 : br.getQuantity();
            if (qty <= 0) continue;

            String roomTypeId = br.getRoomTypeId();
            if (roomTypeId == null || roomTypeId.isBlank()) continue;

            map.put(roomTypeId, map.getOrDefault(roomTypeId, 0) + qty);
        }

        return map.entrySet().stream()
                .map(e -> new RoomRequestDTO(e.getKey(), e.getValue()))
                .toList();
    }

    @Transactional
    public int claimGuestBookingsToUser(String sid, Long userId) {
        if (userId == null) throw new IllegalArgumentException("Thiếu userId");
        if (sid == null || sid.isBlank()) return 0;

        var list = hotelBookingRepository
            .findByGuestSessionIdAndUserIdIsNullOrderByCreatedAtDesc(sid);

        if (list.isEmpty()) return 0;

        for (var b : list) {
            b.setUserId(userId);
            b.setGuestSessionId(null); // hoặc giữ lại nếu bạn vẫn muốn “đơn trên thiết bị”
        }

        hotelBookingRepository.saveAll(list);
        return list.size();
    }
}
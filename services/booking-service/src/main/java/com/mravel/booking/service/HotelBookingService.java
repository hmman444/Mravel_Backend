// src/main/java/com/mravel/booking/service/HotelBookingService.java
package com.mravel.booking.service;

import com.mravel.booking.client.CatalogInventoryClient;
import com.mravel.booking.client.CatalogInventoryDtos.*;
import com.mravel.booking.dto.HotelBookingDtos.CreateHotelBookingRequest;
import com.mravel.booking.dto.HotelBookingDtos.SelectedRoom;
import com.mravel.booking.dto.HotelBookingDtos.HotelBookingCreatedDTO;
import com.mravel.booking.model.Booking;
import com.mravel.booking.model.Booking.PayOption;
import com.mravel.booking.model.Booking.BookingStatus;
import com.mravel.booking.model.Booking.PaymentStatus;
import com.mravel.booking.model.BookingRoom;
import com.mravel.booking.payment.MomoGatewayClient;
import com.mravel.booking.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.scheduling.annotation.Scheduled;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class HotelBookingService {

    private final BookingRepository bookingRepository;
    private final CatalogInventoryClient catalogInventoryClient;
    private final MomoGatewayClient momoPaymentService;

    // =====================================================================
    //                          TẠO BOOKING HOTEL
    // =====================================================================

    /**
     * Flow:
     * 1. Validate request.
     * 2. Gọi CatalogInventoryClient.checkAvailability() để đảm bảo còn phòng.
     * 3. Tính tổng tiền, tiền cọc, amountPayable.
     * 4. Tạo Booking + BookingRooms (status = PENDING_PAYMENT).
     * 5. Gọi Momo tạo payment, lấy payUrl.
     * 6. Trả về DTO chứa booking + payUrl để FE/Postman dùng.
     */
    @Transactional
    public HotelBookingCreatedDTO createHotelBooking(CreateHotelBookingRequest req) {
        validateRequest(req);

        LocalDate today = LocalDate.now();
        if (req.checkInDate().isBefore(today)) {
            throw new IllegalArgumentException("Ngày nhận phòng phải từ hôm nay trở đi");
        }
        if (!req.checkOutDate().isAfter(req.checkInDate())) {
            throw new IllegalArgumentException("Ngày trả phòng phải sau ngày nhận phòng");
        }

        int nights = (int) ChronoUnit.DAYS.between(req.checkInDate(), req.checkOutDate());
        if (nights <= 0) {
            throw new IllegalArgumentException("Ngày nhận / trả phòng không hợp lệ");
        }

        // 1. Build roomRequests từ SelectedRoom
        List<RoomRequestDTO> roomRequests = buildRoomRequestsFromSelectedRooms(req.rooms());

        // 2. TRỪ TỒN KHO NGAY LÚC TẠO BOOKING (HOLD PHÒNG)
        //    Nếu hết phòng -> phương thức này sẽ ném exception -> booking không được tạo
        catalogInventoryClient.holdInventory(
            new DeductInventoryRequest(
                req.hotelId(),
                req.hotelSlug(),
                req.checkInDate(),
                req.checkOutDate(),
                roomRequests
            )
        );

        // 3. Tính totalAmount + build BookingRoom như cũ
        List<BookingRoom> roomEntities = new ArrayList<>();
        BigDecimal totalAmount = BigDecimal.ZERO;

        int totalRoomsCount = 0;

        for (SelectedRoom sr : req.rooms()) {
            if (sr.quantity() == null || sr.quantity() <= 0) continue;
            if (sr.pricePerNight() == null) {
                throw new IllegalArgumentException("Thiếu giá/đêm cho phòng " + sr.roomTypeId());
            }

            BigDecimal lineTotal = sr.pricePerNight()
                    .multiply(BigDecimal.valueOf(nights))
                    .multiply(BigDecimal.valueOf(sr.quantity()));

            totalAmount = totalAmount.add(lineTotal);

            totalRoomsCount += sr.quantity();

            BookingRoom br = BookingRoom.builder()
                    .roomTypeId(sr.roomTypeId())
                    .roomTypeName(sr.roomTypeName())
                    .ratePlanId(sr.ratePlanId())
                    .ratePlanName(sr.ratePlanName())
                    .quantity(sr.quantity())
                    .nights(nights)
                    .pricePerNight(sr.pricePerNight())
                    .totalAmount(lineTotal)
                    .build();

            roomEntities.add(br);
        }

        if (totalAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Tổng tiền booking phải lớn hơn 0");
        }

        PayOption payOptionEnum = PayOption.valueOf(req.payOption());
        String currency = "VND";

        BigDecimal depositAmount = calculateDeposit(totalAmount, payOptionEnum);
        BigDecimal amountPayable = payOptionEnum == PayOption.FULL ? totalAmount : depositAmount;

        // 4. Tạo Booking entity – inventoryDeducted = true (đã giữ phòng)
        Booking booking = Booking.builder()
                .code(generateCode())
                .type(Booking.BookingType.HOTEL)
                .userId(req.userId())
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
                .payOption(payOptionEnum)
                .totalAmount(totalAmount)
                .depositAmount(depositAmount)
                .amountPayable(amountPayable)
                .amountPaid(BigDecimal.ZERO)
                .currencyCode(currency)
                .status(BookingStatus.PENDING_PAYMENT)
                .paymentStatus(PaymentStatus.PENDING)
                .inventoryDeducted(true)  // 🔥 đã trừ tồn kho
                .build();

        roomEntities.forEach(br -> br.setBooking(booking));
        booking.setRooms(roomEntities);

        Booking saved = bookingRepository.save(booking);

        // 5. Gọi MoMo tạo payment như cũ
        String payUrl = momoPaymentService.createPayment(
                saved.getCode(),
                saved.getAmountPayable(),
                saved.getHotelName()
        );

        return HotelBookingMapper.toCreatedDTO(
                saved,
                "MOMO_WALLET",
                payUrl
        );
    }

    // =====================================================================
    //             SAU KHI THANH TOÁN THÀNH CÔNG -> TRỪ TỒN KHO + CONFIRM
    // =====================================================================

    @Transactional
    public Booking markHotelBookingPaidAndConfirm(String bookingCode, BigDecimal paidAmount) {
        Booking booking = bookingRepository.findByCode(bookingCode)
                .orElseThrow(() -> new IllegalArgumentException("Booking không tồn tại"));

        if (booking.getType() != Booking.BookingType.HOTEL) {
            throw new IllegalStateException("Booking này không phải loại HOTEL");
        }

        // Idempotent: nếu đã confirmed/paid rồi thì trả luôn
        if (booking.getStatus() != BookingStatus.PENDING_PAYMENT) {
            return booking;
        }

        // 1) COMMIT inventory: held -> booked (chỉ khi trước đó có hold)
        if (Boolean.TRUE.equals(booking.getInventoryDeducted())) {
            List<RoomRequestDTO> roomRequests = buildRoomRequestsFromBooking(booking);

            catalogInventoryClient.commitInventory(
                    new DeductInventoryRequest(
                            booking.getHotelId(),
                            booking.getHotelSlug(),
                            booking.getCheckInDate(),
                            booking.getCheckOutDate(),
                            roomRequests
                    )
            );
            // booking.getInventoryDeducted() vẫn có thể giữ true
            // vì giờ inventory đã "reserved" dưới dạng BOOKED.
        }

        // 2) Update trạng thái thanh toán
        BigDecimal finalPaid = (paidAmount != null && paidAmount.compareTo(BigDecimal.ZERO) > 0)
                ? paidAmount
                : booking.getAmountPayable();

        booking.setAmountPaid(finalPaid);
        booking.setPaidAt(Instant.now());
        booking.setPaymentStatus(PaymentStatus.SUCCESS);
        booking.setStatus(BookingStatus.CONFIRMED);

        return bookingRepository.save(booking);
    }

    // =====================================================================
    //                          HỦY BOOKING HOTEL
    // =====================================================================

    @Transactional
    public Booking cancelHotelBooking(String bookingCode, Long userId, String reason) {
        Booking booking = bookingRepository.findByCode(bookingCode)
                .orElseThrow(() -> new IllegalArgumentException("Booking không tồn tại"));

        if (booking.getType() != Booking.BookingType.HOTEL) {
            throw new IllegalStateException("Booking này không phải loại HOTEL");
        }

        if (userId != null && !userId.equals(booking.getUserId())) {
            throw new IllegalStateException("Bạn không có quyền hủy booking này");
        }

        if (booking.getStatus() == BookingStatus.CANCELLED
                || booking.getStatus() == BookingStatus.REFUNDED
                || booking.getStatus() == BookingStatus.COMPLETED) {
            return booking;
        }

        BookingStatus oldStatus = booking.getStatus();

        long minutesFromCreate = Duration.between(
                booking.getCreatedAt(),
                Instant.now()
        ).toMinutes();

        int freeCancelMinutes = 30; // sau này có thể lấy từ bookingConfig snapshot

        booking.setCancelReason(reason);
        booking.setCancelledAt(Instant.now());

        // 1) Set trạng thái mới
        if (minutesFromCreate <= freeCancelMinutes) {
            booking.setStatus(BookingStatus.REFUNDED);
            booking.setPaymentStatus(PaymentStatus.REFUNDED);
            // TODO: Call refund MoMo nếu bạn muốn
        } else {
            if (booking.getPayOption() == PayOption.DEPOSIT) {
                booking.setStatus(BookingStatus.CANCELLED);
                // paymentStatus tuỳ bạn: FAILED/CANCELLED/...
            } else {
                booking.setStatus(BookingStatus.REFUNDED);
                booking.setPaymentStatus(PaymentStatus.REFUNDED);
                // TODO: Call refund MoMo nếu muốn
            }
        }

        // 2) Inventory rollback/release dựa trên OLD status
        if (Boolean.TRUE.equals(booking.getInventoryDeducted())) {
            List<RoomRequestDTO> roomRequests = buildRoomRequestsFromBooking(booking);

            // Nếu lúc trước đang PENDING_PAYMENT => release hold
            if (oldStatus == BookingStatus.PENDING_PAYMENT) {
                catalogInventoryClient.releaseHold(
                        new RollbackInventoryRequest(
                                booking.getHotelId(),
                                booking.getCheckInDate(),
                                booking.getCheckOutDate(),
                                roomRequests
                        )
                );
                booking.setInventoryDeducted(false);
            }
            // Nếu lúc trước đã CONFIRMED (đã commit bookedRooms) => rollback bookedRooms
            else if (oldStatus == BookingStatus.CONFIRMED || oldStatus == BookingStatus.PAID) {
                catalogInventoryClient.rollbackInventory(
                        new RollbackInventoryRequest(
                                booking.getHotelId(),
                                booking.getCheckInDate(),
                                booking.getCheckOutDate(),
                                roomRequests
                        )
                );
                booking.setInventoryDeducted(false);
            }
        }

        return bookingRepository.save(booking);
    }

    // =====================================================================
    //                           HELPERS
    // =====================================================================

    private void validateRequest(CreateHotelBookingRequest req) {
        if (req == null) {
            throw new IllegalArgumentException("Request không được null");
        }
        if (req.contactName() == null || req.contactName().isBlank()) {
            throw new IllegalArgumentException("contactName không được trống");
        }
        if (req.contactPhone() == null || req.contactPhone().isBlank()) {
            throw new IllegalArgumentException("contactPhone không được trống");
        }
        if (req.hotelId() == null || req.hotelSlug() == null) {
            throw new IllegalArgumentException("Thiếu thông tin khách sạn");
        }
        if (req.checkInDate() == null || req.checkOutDate() == null) {
            throw new IllegalArgumentException("Thiếu ngày nhận/trả phòng");
        }
        if (req.rooms() == null || req.rooms().isEmpty()) {
            throw new IllegalArgumentException("Chưa chọn phòng");
        }
    }

    private BigDecimal calculateDeposit(BigDecimal totalAmount, PayOption payOption) {
        if (payOption == PayOption.FULL) {
            return totalAmount;
        }
        BigDecimal percent = BigDecimal.valueOf(0.3);
        return totalAmount.multiply(percent);
    }

    private String generateCode() {
        String random = UUID.randomUUID().toString().replace("-", "").substring(0, 8).toUpperCase();
        return "BK-" + random;
    }

    private List<RoomRequestDTO> buildRoomRequestsFromSelectedRooms(List<SelectedRoom> rooms) {
        if (rooms == null || rooms.isEmpty()) return List.of();

        Map<String, Integer> map = new HashMap<>();
        for (SelectedRoom sr : rooms) {
            if (sr.quantity() == null || sr.quantity() <= 0) continue;
            map.merge(sr.roomTypeId(), sr.quantity(), Integer::sum);
        }

        return map.entrySet().stream()
                .map(e -> new RoomRequestDTO(e.getKey(), e.getValue()))
                .toList();
    }

    private List<RoomRequestDTO> buildRoomRequestsFromBooking(Booking booking) {
        if (booking.getRooms() == null || booking.getRooms().isEmpty()) return List.of();

        Map<String, Integer> map = new HashMap<>();
        for (BookingRoom br : booking.getRooms()) {
            if (br.getQuantity() == null || br.getQuantity() <= 0) continue;
            map.merge(br.getRoomTypeId(), br.getQuantity(), Integer::sum);
        }

        return map.entrySet().stream()
                .map(e -> new RoomRequestDTO(e.getKey(), e.getValue()))
                .toList();
    }

    @Scheduled(fixedDelayString = "${mravel.booking.pending-expire-check-ms:60000}")
    @Transactional
    public void autoCancelPendingHotelBookings() {
        Instant cutoff = Instant.now().minus(30, ChronoUnit.MINUTES);

        List<Booking> pendings = bookingRepository
                .findByTypeAndStatusAndCreatedAtBefore(
                        Booking.BookingType.HOTEL,
                        BookingStatus.PENDING_PAYMENT,
                        cutoff
                );

        if (pendings.isEmpty()) return;

        Instant now = Instant.now();

        for (Booking b : pendings) {
            // nếu ai đó vừa confirm song song thì bỏ
            if (b.getStatus() != BookingStatus.PENDING_PAYMENT) continue;

            b.setStatus(BookingStatus.CANCELLED);
            b.setPaymentStatus(PaymentStatus.FAILED);
            b.setCancelledAt(now);
            b.setCancelReason("AUTO_CANCEL_NOT_PAID_WITHIN_30_MIN");

            // rollback inventory nếu trước đó đã deduct
            if (Boolean.TRUE.equals(b.getInventoryDeducted())) {
                List<RoomRequestDTO> roomRequests = buildRoomRequestsFromBooking(b);
                catalogInventoryClient.releaseHold(
                        new RollbackInventoryRequest(
                                b.getHotelId(),
                                b.getCheckInDate(),
                                b.getCheckOutDate(),
                                roomRequests
                        )
                );
                b.setInventoryDeducted(false);
            }
        }

        bookingRepository.saveAll(pendings);
    }
}
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
import com.mravel.booking.payment.MomoGatewayClient;
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
    private final MomoGatewayClient momoPaymentService;

    @Transactional
    public HotelBookingCreatedDTO createHotelBooking(CreateHotelBookingRequest req) {
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

        String payUrl = momoPaymentService.createPayment(
                saved.getCode(),
                saved.getAmountPayable(),
                saved.getHotelName()
        );

        return HotelBookingMapper.toCreatedDTO(saved, "MOMO_WALLET", payUrl);
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

        return hotelBookingRepository.save(booking);
    }

    @Transactional
    public HotelBooking cancelHotelBooking(String bookingCode, Long userId, String reason) {
        HotelBooking booking = hotelBookingRepository.findByCode(bookingCode)
                .orElseThrow(() -> new IllegalArgumentException("Booking không tồn tại"));

        if (userId != null && !userId.equals(booking.getUserId())) {
            throw new IllegalStateException("Bạn không có quyền hủy booking này");
        }

        if (booking.getStatus() == BookingStatus.CANCELLED
                || booking.getStatus() == BookingStatus.REFUNDED
                || booking.getStatus() == BookingStatus.COMPLETED) {
            return booking;
        }

        BookingStatus oldStatus = booking.getStatus();
        long minutesFromCreate = Duration.between(booking.getCreatedAt(), Instant.now()).toMinutes();

        booking.setCancelReason(reason);
        booking.setCancelledAt(Instant.now());

        // Rule hoàn/không hoàn (giữ y như bạn)
        if (minutesFromCreate <= FREE_CANCEL_MINUTES) {
            booking.setStatus(BookingStatus.REFUNDED);
            booking.setPaymentStatus(PaymentStatus.REFUNDED);
        } else {
            if (booking.getPayOption() == PayOption.DEPOSIT) {
                booking.setStatus(BookingStatus.CANCELLED);
                // paymentStatus tuỳ bạn; tạm để FAILED cho rõ nghĩa là không hoàn
                booking.setPaymentStatus(PaymentStatus.FAILED);
            } else {
                booking.setStatus(BookingStatus.REFUNDED);
                booking.setPaymentStatus(PaymentStatus.REFUNDED);
            }
        }

        // Release/Rollback inventory theo OLD status
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
}
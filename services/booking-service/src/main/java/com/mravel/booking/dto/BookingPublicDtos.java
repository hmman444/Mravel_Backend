package com.mravel.booking.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

public class BookingPublicDtos {

    public record BookingLookupRequest(
            String bookingCode,
            String phoneLast4,
            String email // optional
    ) {}

    /**
     * SUMMARY cho list booking hotel (dùng cho Partner/User list).
     * ✅ Canonical constructor (đầy đủ) khớp mapper mới.
     * ✅ Có thêm constructor "cũ" để tương thích code cũ.
     */
    public record HotelBookingSummaryDTO(
            String code,

            String hotelId,
            String hotelName,
            String hotelSlug,

            LocalDate checkInDate,
            LocalDate checkOutDate,
            Integer nights,
            Integer roomsCount,

            String contactName,
            String contactPhone,
            String contactEmail,

            BigDecimal totalAmount,
            BigDecimal depositAmount,
            BigDecimal amountPayable,
            BigDecimal amountPaid,
            String currencyCode,

            String status,
            String paymentStatus,

            Instant createdAt,
            Instant paidAt,
            Instant cancelledAt,
            String cancelReason
    ) {
        // ✅ Constructor cũ (giữ cho những chỗ đang dùng signature 10 params)
        public HotelBookingSummaryDTO(
                String code,
                String hotelName,
                String hotelSlug,
                LocalDate checkInDate,
                LocalDate checkOutDate,
                Integer roomsCount,
                String status,
                String paymentStatus,
                Instant createdAt,
                Instant paidAt
        ) {
            this(
                    code,
                    null, hotelName, hotelSlug,
                    checkInDate, checkOutDate, null, roomsCount,
                    null, null, null,
                    null, null, null, null, null,
                    status, paymentStatus,
                    createdAt, paidAt,
                    null, null
            );
        }
    }
}
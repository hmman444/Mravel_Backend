// src/main/java/com/mravel/booking/dto/BookingPublicDtos.java
package com.mravel.booking.dto;

import java.time.Instant;
import java.time.LocalDate;

public class BookingPublicDtos {

    public record BookingLookupRequest(
            String bookingCode,
            String phoneLast4,
            String email // optional
    ) {}

    public record HotelBookingSummaryDTO(
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
    ) {}
}
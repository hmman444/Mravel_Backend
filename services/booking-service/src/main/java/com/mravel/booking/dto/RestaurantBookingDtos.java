// src/main/java/com/mravel/booking/dto/restaurant/RestaurantBookingDtos.java
package com.mravel.booking.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class RestaurantBookingDtos {

    public record SelectedTable(
        String tableTypeId,
        String tableTypeName,
        Integer seats,
        Integer quantity,
        BigDecimal depositPrice
    ) {}

    public record CreateRestaurantBookingRequest(
        Long userId,
        String contactName,
        String contactPhone,
        String contactEmail,
        String note,

        String restaurantId,
        String restaurantSlug,
        String restaurantName,

        LocalDate reservationDate,
        LocalTime reservationTime,
        Integer durationMinutes,
        Integer people,

        List<SelectedTable> tables
    ) {}

    public record RestaurantBookingSummaryDTO(
        String code,
        String restaurantName,
        String restaurantSlug,
        LocalDate reservationDate,
        LocalTime reservationTime,
        Integer people,
        Integer tablesCount,
        String status,
        String paymentStatus,
        Instant createdAt,
        Instant paidAt
    ) {}

    public record RestaurantBookingCreatedDTO(
        String bookingCode,
        String restaurantName,
        String restaurantSlug,
        LocalDate reservationDate,
        LocalTime reservationTime,
        Integer durationMinutes,
        Integer people,
        Integer tablesCount,

        String payOption,       // DEPOSIT
        BigDecimal depositAmount,
        BigDecimal amountPayable,
        String currencyCode,

        String paymentMethod,
        String paymentUrl
    ) {}

    public record BookingTableLine(
        Long id,
        String tableTypeId,
        String tableTypeName,
        Integer seats,
        Integer quantity,
        BigDecimal depositPrice,
        BigDecimal totalDeposit
    ) {}

    public record RestaurantBookingDetailDTO(
        Long id,
        Instant createdAt,
        String code,
        Long userId,
        String guestSessionId,

        String contactName,
        String contactPhone,
        String contactEmail,
        String note,

        String restaurantId,
        String restaurantSlug,
        String restaurantName,

        LocalDate reservationDate,
        LocalTime reservationTime,
        Integer durationMinutes,
        Integer people,
        Integer tablesCount,

        String status,
        String paymentStatus,
        BigDecimal depositAmount,
        BigDecimal amountPayable,
        BigDecimal amountPaid,
        Instant paidAt,
        Instant cancelledAt,
        String cancelReason,

        Boolean inventoryDeducted,
        List<BookingTableLine> tables
    ) {}
}
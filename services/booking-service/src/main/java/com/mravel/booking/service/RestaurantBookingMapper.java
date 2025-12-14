package com.mravel.booking.service;

import com.mravel.booking.dto.RestaurantBookingDtos.*;
import com.mravel.booking.model.BookingTable;
import com.mravel.booking.model.RestaurantBooking;

import java.util.Collections;
import java.util.List;

public final class RestaurantBookingMapper {

    private RestaurantBookingMapper() {}

    public static RestaurantBookingSummaryDTO toSummary(RestaurantBooking b) {
        if (b == null) return null;

        return new RestaurantBookingSummaryDTO(
            b.getCode(),
            b.getRestaurantName(),
            b.getRestaurantSlug(),
            b.getReservationDate(),
            b.getReservationTime(),
            b.getPeople(),
            b.getTablesCount(),
            b.getStatus() == null ? null : b.getStatus().name(),
            b.getPaymentStatus() == null ? null : b.getPaymentStatus().name(),
            b.getCreatedAt(),
            b.getPaidAt()
        );
    }

    public static RestaurantBookingDetailDTO toDetailDTO(RestaurantBooking b) {
        if (b == null) return null;

        List<BookingTableLine> lines = mapLines(b.getTables());

        return new RestaurantBookingDetailDTO(
            b.getId(),
            b.getCreatedAt(),
            b.getCode(),
            b.getUserId(),
            b.getGuestSessionId(),

            b.getContactName(),
            b.getContactPhone(),
            b.getContactEmail(),
            b.getNote(),

            b.getRestaurantId(),
            b.getRestaurantSlug(),
            b.getRestaurantName(),

            b.getReservationDate(),
            b.getReservationTime(),
            b.getDurationMinutes(),
            b.getPeople(),
            b.getTablesCount(),

            b.getStatus() == null ? null : b.getStatus().name(),
            b.getPaymentStatus() == null ? null : b.getPaymentStatus().name(),
            b.getDepositAmount(),
            b.getAmountPayable(),
            b.getAmountPaid(),
            b.getPaidAt(),
            b.getCancelledAt(),
            b.getCancelReason(),

            b.getInventoryDeducted(),
            lines
        );
    }

    private static List<BookingTableLine> mapLines(List<BookingTable> tables) {
        if (tables == null || tables.isEmpty()) return Collections.emptyList();

        return tables.stream()
            .filter(t -> t != null)
            .map(t -> new BookingTableLine(
                t.getId(),
                t.getTableTypeId(),
                t.getTableTypeName(),
                t.getSeats(),
                t.getQuantity(),
                t.getDepositPrice(),
                t.getTotalDeposit()
            ))
            .toList();
    }
}
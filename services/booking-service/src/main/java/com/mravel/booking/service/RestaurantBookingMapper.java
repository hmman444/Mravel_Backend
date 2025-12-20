package com.mravel.booking.service;

import com.mravel.booking.dto.RestaurantBookingDtos.BookingTableLine;
import com.mravel.booking.dto.RestaurantBookingDtos.RestaurantBookingDetailDTO;
import com.mravel.booking.dto.RestaurantBookingDtos.RestaurantBookingSummaryDTO;
import com.mravel.booking.model.BookingTable;
import com.mravel.booking.model.RestaurantBooking;

import java.util.Collections;
import java.util.List;

public final class RestaurantBookingMapper {

    private RestaurantBookingMapper() {}

    /*
     * ✅ DTO EXPECTED (SUMMARY) - bạn cần update record/constructor cho RestaurantBookingSummaryDTO:
     * RestaurantBookingSummaryDTO(
     *   String code,
     *   String restaurantId,
     *   String restaurantName,
     *   String restaurantSlug,
     *
     *   java.time.LocalDate reservationDate,
     *   java.time.LocalTime reservationTime,
     *   Integer durationMinutes,
     *   Integer people,
     *   Integer tablesCount,
     *
     *   String contactName,
     *   String contactPhone,
     *   String contactEmail,
     *
     *   java.math.BigDecimal totalAmount,
     *   java.math.BigDecimal depositAmount,
     *   java.math.BigDecimal amountPayable,
     *   java.math.BigDecimal amountPaid,
     *   String currencyCode,
     *
     *   String bookingStatus,
     *   String paymentStatus,
     *   java.time.Instant createdAt,
     *   java.time.Instant paidAt,
     *   java.time.Instant cancelledAt,
     *   String cancelReason
     * )
     */

    // ===================== SUMMARY (CHO LIST) =====================
    public static RestaurantBookingSummaryDTO toSummary(RestaurantBooking b) {
        if (b == null) return null;

        return new RestaurantBookingSummaryDTO(
                b.getCode(),

                b.getRestaurantId(),
                b.getRestaurantName(),
                b.getRestaurantSlug(),

                b.getReservationDate(),
                b.getReservationTime(),
                b.getDurationMinutes(),
                b.getPeople(),
                b.getTablesCount(),

                b.getContactName(),
                b.getContactPhone(),
                b.getContactEmail(),

                b.getTotalAmount(),
                b.getDepositAmount(),
                b.getAmountPayable(),
                b.getAmountPaid(),
                b.getCurrencyCode(),

                b.getStatus() == null ? null : b.getStatus().name(),
                b.getPaymentStatus() == null ? null : b.getPaymentStatus().name(),
                b.getCreatedAt(),
                b.getPaidAt(),
                b.getCancelledAt(),
                b.getCancelReason()
        );
    }

    // ===================== DETAIL (CHO DETAIL) =====================
    public static RestaurantBookingDetailDTO toDetail(RestaurantBooking b) {
        return toDetailDTO(b);
    }

    public static RestaurantBookingDetailDTO toDetailDTO(RestaurantBooking b) {
        if (b == null) return null;

        List<BookingTableLine> lines = mapLines(b.getTables());

        // Nếu DTO detail của bạn hiện CHƯA có updatedAt/totalAmount/currencyCode/payOption...
        // thì bạn có 2 lựa chọn:
        // 1) Update DTO cho "đầy đủ" như Hotel (khuyến nghị)
        // 2) Giữ signature cũ và bỏ bớt field tương ứng
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

                // tiền
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
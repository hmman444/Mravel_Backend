package com.mravel.booking.service;

import com.mravel.booking.dto.BookingPublicDtos.HotelBookingSummaryDTO;
import com.mravel.booking.dto.HotelBookingDtos.BookingRoomLine;
import com.mravel.booking.dto.HotelBookingDtos.HotelBookingCreatedDTO;
import com.mravel.booking.dto.HotelBookingDtos.HotelBookingDetailDTO;
import com.mravel.booking.model.BookingRoom;
import com.mravel.booking.model.HotelBooking;

import java.util.Collections;
import java.util.List;

public final class HotelBookingMapper {

    private HotelBookingMapper() {}

    /*
     * ✅ DTO EXPECTED (SUMMARY) - bạn cần update record/constructor cho HotelBookingSummaryDTO:
     * HotelBookingSummaryDTO(
     *   String code,
     *   String hotelId,
     *   String hotelName,
     *   String hotelSlug,
     *   java.time.LocalDate checkInDate,
     *   java.time.LocalDate checkOutDate,
     *   Integer nights,
     *   Integer roomsCount,
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
    public static HotelBookingSummaryDTO toSummary(HotelBooking b) {
        if (b == null) return null;

        return new HotelBookingSummaryDTO(
                b.getCode(),

                b.getHotelId(),
                b.getHotelName(),
                b.getHotelSlug(),

                b.getCheckInDate(),
                b.getCheckOutDate(),
                b.getNights(),
                b.getRoomsCount(),

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

    // ===================== CREATED (SAU KHI TẠO) =====================
    public static HotelBookingCreatedDTO toCreatedDTO(
            HotelBooking b,
            String paymentMethod,
            String paymentUrl
    ) {
        if (b == null) return null;

        return new HotelBookingCreatedDTO(
                b.getCode(),
                b.getHotelName(),
                b.getHotelSlug(),
                b.getCheckInDate(),
                b.getCheckOutDate(),
                b.getNights(),
                b.getRoomsCount(),
                b.getPayOption() == null ? null : b.getPayOption().name(),
                b.getTotalAmount(),
                b.getDepositAmount(),
                b.getAmountPayable(),
                b.getCurrencyCode(),
                paymentMethod,
                paymentUrl
        );
    }

    // ===================== DETAIL (CHO DETAIL) =====================
    public static HotelBookingDetailDTO toDetail(HotelBooking b) {
        return toDetailDTO(b);
    }

    public static HotelBookingDetailDTO toDetailDTO(HotelBooking b) {
        if (b == null) return null;

        List<BookingRoomLine> roomLines =
                (b.getRooms() == null) ? Collections.emptyList()
                        : b.getRooms().stream().map(HotelBookingMapper::toRoomLine).toList();

        return new HotelBookingDetailDTO(
                b.getId(),
                b.getCreatedAt(),
                b.getUpdatedAt(),

                b.getCode(),
                b.getUserId(),
                b.getGuestSessionId(),

                b.getContactName(),
                b.getContactPhone(),
                b.getContactEmail(),
                b.getNote(),

                b.getHotelId(),
                b.getHotelSlug(),
                b.getHotelName(),

                b.getCheckInDate(),
                b.getCheckOutDate(),
                b.getNights(),
                b.getRoomsCount(),

                b.getPayOption() != null ? b.getPayOption().name() : null,
                b.getTotalAmount(),
                b.getDepositAmount(),
                b.getAmountPayable(),
                b.getAmountPaid(),
                b.getCurrencyCode(),

                b.getStatus() != null ? b.getStatus().name() : null,
                b.getPaymentStatus() != null ? b.getPaymentStatus().name() : null,
                b.getPaidAt(),
                b.getCancelledAt(),
                b.getCancelReason(),

                b.getInventoryDeducted(),
                roomLines
        );
    }

    private static BookingRoomLine toRoomLine(BookingRoom r) {
        if (r == null) return null;

        return new BookingRoomLine(
                r.getId(),
                r.getRoomTypeId(),
                r.getRoomTypeName(),
                r.getRatePlanId(),
                r.getRatePlanName(),
                r.getQuantity(),
                r.getNights(),
                r.getPricePerNight(),
                r.getTotalAmount()
        );
    }
}
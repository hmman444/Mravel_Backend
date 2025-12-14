// src/main/java/com/mravel/booking/service/HotelBookingMapper.java
package com.mravel.booking.service;

import com.mravel.booking.dto.HotelBookingDtos.BookingRoomLine;
import com.mravel.booking.dto.HotelBookingDtos.HotelBookingCreatedDTO;
import com.mravel.booking.dto.HotelBookingDtos.HotelBookingDetailDTO;
import com.mravel.booking.model.BookingRoom;
import com.mravel.booking.model.HotelBooking;

import java.util.List;

public class HotelBookingMapper {

    public static HotelBookingCreatedDTO toCreatedDTO(
            HotelBooking b,
            String paymentMethod,
            String paymentUrl
    ) {
        return new HotelBookingCreatedDTO(
                b.getCode(),
                b.getHotelName(),
                b.getHotelSlug(),
                b.getCheckInDate(),
                b.getCheckOutDate(),
                b.getNights(),
                b.getRoomsCount(),
                b.getPayOption().name(),
                b.getTotalAmount(),
                b.getDepositAmount(),
                b.getAmountPayable(),
                b.getCurrencyCode(),
                paymentMethod,
                paymentUrl
        );
    }

    public static HotelBookingDetailDTO toDetailDTO(HotelBooking b) {
        List<BookingRoomLine> roomLines = (b.getRooms() == null) ? List.of()
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
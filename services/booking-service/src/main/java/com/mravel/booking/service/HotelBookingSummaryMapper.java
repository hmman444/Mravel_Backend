package com.mravel.booking.service;

import com.mravel.booking.dto.BookingPublicDtos.HotelBookingSummaryDTO;
import com.mravel.booking.model.HotelBooking;

public final class HotelBookingSummaryMapper {

    private HotelBookingSummaryMapper() {}

    public static HotelBookingSummaryDTO toSummary(HotelBooking b) {
        return new HotelBookingSummaryDTO(
                b.getCode(),
                b.getHotelName(),
                b.getHotelSlug(),
                b.getCheckInDate(),
                b.getCheckOutDate(),
                b.getRoomsCount(),
                String.valueOf(b.getStatus()),
                String.valueOf(b.getPaymentStatus()),
                b.getCreatedAt(),
                b.getPaidAt()
        );
    }
}
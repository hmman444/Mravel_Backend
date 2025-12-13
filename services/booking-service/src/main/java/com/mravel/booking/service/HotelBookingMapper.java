// src/main/java/com/mravel/booking/service/HotelBookingMapper.java
package com.mravel.booking.service;

import com.mravel.booking.dto.HotelBookingDtos.HotelBookingCreatedDTO;
import com.mravel.booking.model.Booking;

public class HotelBookingMapper {

    public static HotelBookingCreatedDTO toCreatedDTO(
            Booking b,
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
}
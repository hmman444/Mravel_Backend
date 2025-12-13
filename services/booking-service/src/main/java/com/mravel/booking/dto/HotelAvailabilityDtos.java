package com.mravel.booking.dto;

public class HotelAvailabilityDtos {

    public record HotelAvailabilityResponse(
            int remainingRooms,   // min remaining trong range
            boolean isEnough,     // remainingRooms >= requestedRooms
            int requestedRooms
    ) {}
}
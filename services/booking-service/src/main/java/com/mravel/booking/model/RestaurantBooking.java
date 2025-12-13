package com.mravel.booking.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "restaurant_bookings")
@Getter @Setter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class RestaurantBooking extends BookingBase {

    private String restaurantId;
    private String restaurantSlug;
    private String restaurantName;

    private LocalDate reservationDate;
    private LocalTime reservationTime;
    private Integer people;
}
// src/main/java/com/mravel/booking/model/BookingRoom.java
package com.mravel.booking.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Entity
@Table(name = "booking_rooms")
@Getter @Setter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class BookingRoom extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_booking_id", nullable = false)
    private HotelBooking booking;

    @Column(nullable = false)
    private String roomTypeId;

    @Column(nullable = false, length = 200)
    private String roomTypeName;

    private String ratePlanId;
    private String ratePlanName;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Integer nights;

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal pricePerNight;

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal totalAmount;
}
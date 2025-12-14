package com.mravel.booking.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "hotel_bookings")
@Getter @Setter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class HotelBooking extends BookingBase {

    private String hotelId;
    private String hotelSlug;
    private String hotelName;

    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Integer nights;

    @Column
    private Integer roomsCount;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookingRoom> rooms;
}
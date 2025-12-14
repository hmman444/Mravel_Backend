// src/main/java/com/mravel/booking/model/BookingTable.java
package com.mravel.booking.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Entity
@Table(name = "booking_tables")
@Getter @Setter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class BookingTable extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_booking_id", nullable = false)
    private RestaurantBooking booking;

    @Column(nullable = false)
    private String tableTypeId;

    @Column(nullable = false, length = 200)
    private String tableTypeName;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Integer seats;

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal depositPrice;

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal totalDeposit;
}
// src/main/java/com/mravel/booking/model/BookingRoom.java
package com.mravel.booking.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "booking_rooms")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingRoom extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    // Tham chiếu sang HotelDoc.RoomType + RatePlan
    @Column(nullable = false)
    private String roomTypeId;       // HotelDoc.RoomType.id

    @Column(nullable = false, length = 200)
    private String roomTypeName;     // snapshot tên phòng

    private String ratePlanId;       // HotelDoc.RatePlan.id
    private String ratePlanName;     // snapshot tên gói

    @Column(nullable = false)
    private Integer quantity;        // số phòng loại này

    @Column(nullable = false)
    private Integer nights;          // số đêm áp dụng cho loại phòng này

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal pricePerNight;   // giá/đêm tại thời điểm đặt (đã apply khuyến mãi)

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal totalAmount;     // = pricePerNight * nights * quantity
}
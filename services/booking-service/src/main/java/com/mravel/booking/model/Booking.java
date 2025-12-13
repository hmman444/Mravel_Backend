// src/main/java/com/mravel/booking/model/Booking.java
package com.mravel.booking.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking extends BaseEntity {

    // ================== THÔNG TIN CHUNG ==================

    @Column(nullable = false, unique = true, length = 32)
    private String code; // mã booking hiển thị cho user, vd: BK20251208ABC

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private BookingType type; // HOTEL / RESTAURANT

    private Long userId; // id user bên auth/user-service

    // Thông tin contact snapshot
    @Column(nullable = false, length = 100)
    private String contactName;

    @Column(nullable = false, length = 20)
    private String contactPhone;

    @Column(length = 150)
    private String contactEmail;

    @Column(length = 500)
    private String note; // ghi chú của khách

    // ================== LIÊN KẾT HOTEL / RESTAURANT ==================

    // HOTEL
    private String hotelId;      // id trong Mongo (HotelDoc.id)
    private String hotelSlug;    // dùng join với FE/SEO
    private String hotelName;    // snapshot tên tại thời điểm đặt

    // RESTAURANT (không dùng trong flow này)
    private String restaurantId;
    private String restaurantSlug;
    private String restaurantName;

    // ================== THỜI GIAN / SỐ LƯỢNG ==================

    // HOTEL
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Integer nights;           // số đêm (checkOut - checkIn)

    // RESTAURANT
    private LocalDate reservationDate;
    private LocalTime reservationTime;
    private Integer people;

    @Column
    private Integer roomsCount;

    // ================== TIỀN / HÌNH THỨC THANH TOÁN ==================

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PayOption payOption; // FULL / DEPOSIT

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal totalAmount;    // tổng giá trị booking (hotel: tiền phòng)

    @Column(precision = 18, scale = 2)
    private BigDecimal depositAmount;  // tiền cọc (hotel: theo %)

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal amountPayable;  // số tiền phải thanh toán ngay

    @Column(precision = 18, scale = 2)
    private BigDecimal amountPaid;     // số tiền thực tế đã thanh toán qua cổng payment

    @Column(length = 10)
    private String currencyCode;       // "VND"

    // ================== TRẠNG THÁI ==================

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private BookingStatus status; // PENDING_PAYMENT, PAID, CONFIRMED, CANCELLED, REFUNDED...

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PaymentStatus paymentStatus; // PENDING, SUCCESS, FAILED, REFUNDED...

    private Instant paidAt;
    private Instant cancelledAt;

    // Lý do hủy (user/admin)
    @Column(length = 500)
    private String cancelReason;

    /**
     * Đã trừ tồn kho bên catalog hay chưa.
     * - false: mới check availability, chưa trừ.
     * - true: đã gọi deductInventoryForBooking.
     */
    @Column(nullable = false)
    @Builder.Default
    private Boolean inventoryDeducted = false;

    // ================== QUAN HỆ CON ==================

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookingRoom> rooms; // chỉ dùng khi type = HOTEL

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Payment> payments;  // 1 booking có thể có nhiều lần payment

    // ================== ENUMS ==================

    public enum BookingType {
        HOTEL,
        RESTAURANT
    }

    public enum PayOption {
        FULL,       // thanh toán toàn bộ
        DEPOSIT     // thanh toán cọc
    }

    public enum BookingStatus {
        PENDING_PAYMENT, // mới tạo, chưa thanh toán
        PAID,            // đã thanh toán (ít nhất một phần, tùy payOption)
        CONFIRMED,       // đã xác nhận giữ phòng/bàn
        CANCELLED,       // đã hủy
        COMPLETED,       // đã sử dụng xong dịch vụ
        REFUNDED         // đã hoàn tiền (toàn bộ)
    }

    public enum PaymentStatus {
        PENDING,
        SUCCESS,
        FAILED,
        REFUNDED,
        PARTIAL_REFUNDED
    }
}
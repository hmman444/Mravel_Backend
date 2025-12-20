// src/main/java/com/mravel/booking/model/BookingBase.java
package com.mravel.booking.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(
    name = "bookings",
    indexes = {
        @Index(name = "idx_booking_guest_sid", columnList = "guest_session_id"),
        @Index(name = "idx_booking_user_id", columnList = "user_id")
    }
)
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BookingBase extends BaseEntity {

    @Column(nullable = false, unique = true, length = 32)
    private String code;

    @Column(name = "user_id")
    private Long userId;

    // ✅ NEW: guest session id (cookie)
    @Column(name = "guest_session_id", length = 64)
    private String guestSessionId;

    @Column(nullable = false, length = 100)
    private String contactName;

    @Column(nullable = false, length = 20)
    private String contactPhone;

    @Column(length = 150)
    private String contactEmail;

    @Column(length = 500)
    private String note;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PayOption payOption;

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal totalAmount;

    @Column(precision = 18, scale = 2)
    private BigDecimal depositAmount;

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal amountPayable;

    @Column(precision = 18, scale = 2)
    private BigDecimal amountPaid;

    @Column(length = 10)
    private String currencyCode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private BookingStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PaymentStatus paymentStatus;

    private Instant paidAt;
    private Instant cancelledAt;

    @Column(length = 500)
    private String cancelReason;

    @Builder.Default
    @Column(nullable = false)
    private Boolean inventoryDeducted = false;

    @Column(name = "pending_payment_url", length = 1000)
    private String pendingPaymentUrl;

    @Column(name = "pending_payment_order_id", length = 64)
    private String pendingPaymentOrderId;

    @Enumerated(EnumType.STRING)
    @Column(name = "active_payment_method", length = 30)
    private Payment.PaymentMethod activePaymentMethod;

    public enum PayOption { FULL, DEPOSIT }
    public enum BookingStatus {PENDING_PAYMENT, PAID, CONFIRMED, CANCELLED, CANCELLED_BY_PARTNER, COMPLETED, REFUNDED}
    public enum PaymentStatus { PENDING, SUCCESS, FAILED, REFUNDED, PARTIAL_REFUNDED }
}
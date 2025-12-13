// src/main/java/com/mravel/booking/model/Payment.java
package com.mravel.booking.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PaymentMethod method; // MOMO_WALLET, ZALOPAY, VNPAY...

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PaymentStatus status; // PENDING, SUCCESS, FAILED, REFUNDED...

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal amount; // số tiền giao dịch (thường = booking.amountPayable)

    @Column(length = 10)
    private String currencyCode; // "VND"

    // ==== Thông tin từ cổng thanh toán (Momo, ...)

    @Column(length = 50)
    private String provider; // "MOMO"

    @Column(length = 100)
    private String providerRequestId;    // partnerRefId / orderId trong Momo

    @Column(length = 100)
    private String providerTransactionId; // transId / paymentId trả về từ Momo

    @Column(length = 500)
    private String providerPayUrl;      // payUrl để redirect người dùng

    @Column(length = 1000)
    private String providerRawResponse; // lưu JSON/chuỗi response để debug

    private Instant paidAt;

    // ==== Thông tin refund (đơn giản)

    @Column(precision = 18, scale = 2)
    private BigDecimal refundedAmount;

    private Instant refundedAt;

    @Column(length = 1000)
    private String refundRawResponse;

    public enum PaymentMethod {
        MOMO_WALLET,
        CASH_AT_HOTEL,    // nếu sau này bạn muốn hỗ trợ thanh toán tại KS
        OTHER_GATEWAY
    }

    public enum PaymentStatus {
        PENDING,
        SUCCESS,
        FAILED,
        REFUNDED,
        PARTIAL_REFUNDED
    }
}
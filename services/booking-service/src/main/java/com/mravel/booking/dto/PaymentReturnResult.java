package com.mravel.booking.dto;

/**
 * Kết quả khi xử lý return/redirect từ cổng thanh toán (VNPay/MoMo),
 * dùng để build URL điều hướng về trang payment-return của frontend.
 */
public record PaymentReturnResult(String bookingCode, boolean success) {

    public static PaymentReturnResult none() {
        return new PaymentReturnResult(null, false);
    }

    public static PaymentReturnResult failed(String bookingCode) {
        return new PaymentReturnResult(bookingCode, false);
    }
}

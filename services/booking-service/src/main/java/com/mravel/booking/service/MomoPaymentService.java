// src/main/java/com/mravel/booking/service/MomoPaymentService.java
package com.mravel.booking.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.mravel.booking.payment.MomoIpnRequest;
import com.mravel.booking.payment.MomoConfirmRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MomoPaymentService {

    private final HotelBookingService hotelBookingService;

    // createPayment mock hiện giờ bạn có thể bỏ / hoặc để đó

    // 1. Xử lý IPN (giữ nguyên)
    public void handleIpn(MomoIpnRequest body) {
        if (body.getResultCode() != null && body.getResultCode() == 0) {
            String bookingCode = body.getOrderId();
            BigDecimal paidAmount = BigDecimal.valueOf(body.getAmount());
            hotelBookingService.markHotelBookingPaidAndConfirm(bookingCode, paidAmount);
        }
    }

    // 2. Xử lý confirm từ FE / Postman
    public void handleClientConfirm(MomoConfirmRequest body) {
        if (body.getResultCode() != null && body.getResultCode() != 0) {
            // thanh toán thất bại -> tùy bạn muốn làm gì, tạm bỏ qua
            return;
        }
        String bookingCode = body.getOrderId();
        BigDecimal paidAmount = body.getAmount();

        hotelBookingService.markHotelBookingPaidAndConfirm(bookingCode, paidAmount);
    }
}
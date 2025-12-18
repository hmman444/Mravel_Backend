package com.mravel.booking.service;

import java.math.BigDecimal;
import java.time.Instant;

import org.springframework.stereotype.Service;

import com.mravel.booking.model.BookingBase;
import com.mravel.booking.model.Payment;
import com.mravel.booking.payment.momo.MomoConfirmRequest;
import com.mravel.booking.payment.momo.MomoIpnRequest;
import com.mravel.booking.repository.HotelBookingRepository;
import com.mravel.booking.repository.PaymentRepository;
import com.mravel.booking.repository.RestaurantBookingRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MomoPaymentService {

    private final HotelBookingService hotelBookingService;
    private final RestaurantBookingService restaurantBookingService;

    private final HotelBookingRepository hotelBookingRepository;
    private final RestaurantBookingRepository restaurantBookingRepository;
    private final PaymentRepository paymentRepository;

    /**
     * 1) IPN từ MoMo
     * - MoMo gửi orderId (bây giờ có thể là attemptId)
     * - Ta resolve orderId -> booking thật bằng pendingPaymentOrderId (fallback code)
     * - rồi gọi mark...PaidAndConfirm(booking.getCode(), amount)
     */
    public void handleIpn(MomoIpnRequest body) {
        if (body == null) return;

        Integer resultCode = body.getResultCode();
        if (resultCode == null || resultCode != 0) {
            return; // fail/không rõ -> bỏ qua (tuỳ bạn log)
        }

        String orderId = trim(body.getOrderId());
        if (orderId == null) {
            throw new IllegalArgumentException("MoMo IPN thiếu orderId");
        }

        Long amountLong = body.getAmount(); // DTO bạn đang là Long/long
        BigDecimal paidAmount = (amountLong == null) ? null : BigDecimal.valueOf(amountLong);

        resolveAndMarkPaid(orderId, paidAmount, amountLong);
    }

    /**
     * 2) Confirm từ FE / Postman
     * - logic giống IPN
     */
    public void handleClientConfirm(MomoConfirmRequest body) {
        if (body == null) return;

        Integer resultCode = body.getResultCode();
        if (resultCode != null && resultCode != 0) {
            return; // thất bại -> bỏ qua
        }

        String orderId = trim(body.getOrderId());
        if (orderId == null) {
            throw new IllegalArgumentException("Confirm thiếu orderId");
        }

        BigDecimal paidAmount = body.getAmount(); // DTO bạn đang BigDecimal
        Long amountLong = (paidAmount == null) ? null : paidAmount.longValue();

        resolveAndMarkPaid(orderId, paidAmount, amountLong);
    }

    // ========================= core logic =========================
    private void resolveAndMarkPaid(String orderId, BigDecimal paidAmountHotel, Long amountLongForRestaurant) {
    // 0) ưu tiên PaymentAttempt (Payment) trước
    var p = paymentRepository.findByProviderRequestId(orderId).orElse(null);
    if (p != null && p.getBooking() != null) {
        BookingBase booking = p.getBooking();

        // mark payment record SUCCESS (optional nhưng nên làm)
        p.setStatus(Payment.PaymentStatus.SUCCESS);
        p.setPaidAt(Instant.now());
        paymentRepository.save(p);

        if (booking instanceof com.mravel.booking.model.HotelBooking hb) {
        hotelBookingService.markHotelBookingPaidAndConfirm(hb.getCode(), paidAmountHotel);
        return;
        }
        if (booking instanceof com.mravel.booking.model.RestaurantBooking rb) {
        restaurantBookingService.markRestaurantBookingPaidAndConfirm(rb.getCode(), amountLongForRestaurant);
        return;
        }
    }

    // 1) fallback tương thích cũ (orderId == BK-/RB-)
    var hb = hotelBookingRepository.findByCode(orderId).orElse(null);
    if (hb != null) {
        hotelBookingService.markHotelBookingPaidAndConfirm(hb.getCode(), paidAmountHotel);
        return;
    }

    var rb = restaurantBookingRepository.findByCode(orderId).orElse(null);
    if (rb != null) {
        restaurantBookingService.markRestaurantBookingPaidAndConfirm(rb.getCode(), amountLongForRestaurant);
        return;
    }

    throw new IllegalArgumentException("Không tìm thấy booking theo orderId=" + orderId);
    }

    private static String trim(String s) {
        if (s == null) return null;
        String t = s.trim();
        return t.isBlank() ? null : t;
    }
}
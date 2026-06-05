// src/main/java/com/mravel/booking/controller/MomoPaymentController.java
package com.mravel.booking.controller;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mravel.booking.service.HotelBookingService;
import com.mravel.booking.service.RestaurantBookingService;
import com.mravel.booking.service.MomoPaymentService;
import com.mravel.common.response.ApiResponse;
import com.mravel.booking.payment.momo.MomoConfirmRequest;
import com.mravel.booking.payment.momo.MomoIpnRequest;
import com.mravel.booking.repository.HotelBookingRepository;
import com.mravel.booking.repository.RestaurantBookingRepository;

@RestController
@RequestMapping("/api/payment/momo")
@RequiredArgsConstructor
public class MomoPaymentController {

    private final MomoPaymentService momoPaymentService;
    private final HotelBookingService hotelBookingService;
    private final RestaurantBookingService restaurantBookingService;
    @Value("${mravel.frontend.base-url:http://localhost:5173}")
    private String frontendBaseUrl;

    private final HotelBookingRepository hotelBookingRepository;
    private final RestaurantBookingRepository restaurantBookingRepository;

    @PostMapping("/ipn")
    public ResponseEntity<String> handleIpn(@RequestBody MomoIpnRequest body) {
        momoPaymentService.handleIpn(body);
        return ResponseEntity.ok("OK");
    }

    @PostMapping("/confirm")
    public ResponseEntity<ApiResponse<Void>> confirmFromClient(@RequestBody MomoConfirmRequest body) {
        momoPaymentService.handleClientConfirm(body);
        return ResponseEntity.ok(ApiResponse.success("Xác nhận thanh toán thành công", null));
    }

    @GetMapping("/redirect")
    public ResponseEntity<Void> handleRedirect(
            @RequestParam(required = false, name = "orderId") String orderId,
            @RequestParam(required = false, name = "bookingCode") String bookingCode,
            @RequestParam(required = false) Long amount,
            @RequestParam(required = false) Integer resultCode) {

        String oid = (orderId != null && !orderId.isBlank()) ? orderId.trim()
                : (bookingCode != null ? bookingCode.trim() : null);

        String resolvedCode = null; // bookingCode thật để FE poll trạng thái

        if (oid != null && resultCode != null && resultCode == 0) {

            // 1) thử Restaurant trước nếu prefix RB-
            if (oid.startsWith("RB-")) {
                var rb = restaurantBookingRepository.findByCode(oid)
                        .or(() -> restaurantBookingRepository.findByPendingPaymentOrderId(oid))
                        .orElse(null);

                if (rb != null) {
                    restaurantBookingService.markRestaurantBookingPaidAndConfirm(
                            rb.getCode(), // bookingCode thật
                            amount);
                    resolvedCode = rb.getCode();
                } else {
                    // fallback: lỡ oid không phải RB nhưng bị nhầm
                    var hb = hotelBookingRepository.findByCode(oid)
                            .or(() -> hotelBookingRepository.findByPendingPaymentOrderId(oid))
                            .orElse(null);

                    if (hb != null) {
                        hotelBookingService.markHotelBookingPaidAndConfirm(
                                hb.getCode(),
                                amount == null ? null : BigDecimal.valueOf(amount));
                        resolvedCode = hb.getCode();
                    }
                }
            } else {
                // 2) Hotel (BK-...)
                var hb = hotelBookingRepository.findByCode(oid)
                        .or(() -> hotelBookingRepository.findByPendingPaymentOrderId(oid))
                        .orElse(null);

                if (hb != null) {
                    hotelBookingService.markHotelBookingPaidAndConfirm(
                            hb.getCode(), // bookingCode thật
                            amount == null ? null : BigDecimal.valueOf(amount));
                    resolvedCode = hb.getCode();
                } else {
                    // fallback: lỡ oid là restaurant attemptId
                    var rb = restaurantBookingRepository.findByCode(oid)
                            .or(() -> restaurantBookingRepository.findByPendingPaymentOrderId(oid))
                            .orElse(null);

                    if (rb != null) {
                        restaurantBookingService.markRestaurantBookingPaidAndConfirm(
                                rb.getCode(),
                                amount);
                        resolvedCode = rb.getCode();
                    }
                }
            }
        }

        boolean success = resolvedCode != null;
        // Nếu không resolve được mà oid trông giống bookingCode thì vẫn truyền để FE hiển thị
        if (resolvedCode == null && oid != null && (oid.startsWith("BK-") || oid.startsWith("RB-"))) {
            resolvedCode = oid;
        }

        return ResponseEntity.status(HttpStatus.FOUND)
                .location(Objects.requireNonNull(URI.create(buildReturnUrl(resolvedCode, success ? "success" : "failed"))))
                .build();
    }

    /** Build URL điều hướng người dùng về trang kết quả thanh toán của frontend. */
    private String buildReturnUrl(String bookingCode, String status) {
        StringBuilder sb = new StringBuilder(frontendBaseUrl)
                .append("/booking/payment-return?gateway=momo&status=")
                .append(status);
        if (bookingCode != null && !bookingCode.isBlank()) {
            sb.append("&code=").append(URLEncoder.encode(bookingCode, StandardCharsets.UTF_8));
        }
        return sb.toString();
    }
}
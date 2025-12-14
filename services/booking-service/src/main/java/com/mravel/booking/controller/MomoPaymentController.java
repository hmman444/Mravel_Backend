// src/main/java/com/mravel/booking/controller/MomoPaymentController.java
package com.mravel.booking.controller;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mravel.booking.payment.MomoIpnRequest;
import com.mravel.booking.payment.MomoConfirmRequest;
import com.mravel.booking.service.HotelBookingService;
import com.mravel.booking.service.MomoPaymentService;
import com.mravel.common.response.ApiResponse;

@RestController
@RequestMapping("/api/payment/momo")
@RequiredArgsConstructor
public class MomoPaymentController {

    private final MomoPaymentService momoPaymentService;
    private final HotelBookingService hotelBookingService;

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
            @RequestParam(required = false) Integer resultCode
    ) {
        System.out.println(">>> [MoMo REDIRECT] orderId=" + orderId
                + ", bookingCode=" + bookingCode
                + ", amount=" + amount
                + ", resultCode=" + resultCode);

        String code = (orderId != null) ? orderId : bookingCode;

        if (code != null && resultCode != null && resultCode == 0) {
            hotelBookingService.markHotelBookingPaidAndConfirm(
                    code,
                    amount == null ? null : BigDecimal.valueOf(amount)
            );
        }

        String feUrl = "http://localhost:5173/my-bookings";

        // gắn thêm query cho FE show toast nếu thích:
        // String feUrl = "http://localhost:5173/hotels?paid=" + (resultCode != null && resultCode == 0 ? "1" : "0")
        //        + (code != null ? "&bookingCode=" + code : "");

        return ResponseEntity.status(HttpStatus.FOUND) // 302
                .location(URI.create(feUrl))
                .build();
    }
}
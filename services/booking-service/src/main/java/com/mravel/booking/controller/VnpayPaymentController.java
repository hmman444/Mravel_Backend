// src/main/java/com/mravel/booking/controller/VnpayPaymentController.java
package com.mravel.booking.controller;

import com.mravel.booking.service.VnpayPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/payment/vnpay")
@RequiredArgsConstructor
public class VnpayPaymentController {

  private final VnpayPaymentService vnpayPaymentService;

  /** VNPay server-call-server (IPN). Trả JSON RspCode/Message. :contentReference[oaicite:5]{index=5} */
  @GetMapping("/ipn")
  public ResponseEntity<Map<String, String>> ipn(@RequestParam Map<String, String> params) {
    try {
      vnpayPaymentService.handleIpn(params);
      return ResponseEntity.ok(rsp("00", "OK"));
    } catch (IllegalArgumentException bad) {
      return ResponseEntity.ok(rsp("01", "Invalid request"));
    } catch (Exception ex) {
      return ResponseEntity.ok(rsp("99", "Unknown error"));
    }
  }

  @GetMapping("/return")
  public ResponseEntity<Void> vnpReturn(@RequestParam Map<String, String> params) {
    try {
      vnpayPaymentService.handleReturn(params);
    } catch (Exception ignore) {}

    return ResponseEntity.status(HttpStatus.FOUND)
      .location(Objects.requireNonNull(URI.create("http://localhost:5173/my-bookings")))
      .build();
  }

  private static Map<String, String> rsp(String code, String message) {
    Map<String, String> m = new HashMap<>();
    m.put("RspCode", code);
    m.put("Message", message);
    return m;
  }
}
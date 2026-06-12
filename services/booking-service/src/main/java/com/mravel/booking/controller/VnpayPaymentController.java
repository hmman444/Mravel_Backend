// src/main/java/com/mravel/booking/controller/VnpayPaymentController.java
package com.mravel.booking.controller;

import com.mravel.booking.dto.PaymentReturnResult;
import com.mravel.booking.service.VnpayPaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/payment/vnpay")
@RequiredArgsConstructor
@Slf4j
public class VnpayPaymentController {

  private final VnpayPaymentService vnpayPaymentService;

  @Value("${mravel.frontend.base-url:http://localhost:5173}")
  private String frontendBaseUrl;

  /**
   * VNPay server-call-server (IPN). Trả JSON RspCode/Message.
   * :contentReference[oaicite:5]{index=5}
   */
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
    String target;
    try {
      PaymentReturnResult result = vnpayPaymentService.handleReturn(params);
      target = buildReturnUrl(result.bookingCode(), result.success() ? "success" : "failed");
    } catch (Exception ex) {
      log.error("VNPay return failed (vnp_TxnRef={}): {}", params.get("vnp_TxnRef"), ex.getMessage(), ex);
      target = buildReturnUrl(null, "error");
    }

    return ResponseEntity.status(HttpStatus.FOUND)
        .location(Objects.requireNonNull(URI.create(target)))
        .build();
  }

  /** Build URL điều hướng người dùng về trang kết quả thanh toán của frontend. */
  private String buildReturnUrl(String bookingCode, String status) {
    StringBuilder sb = new StringBuilder(frontendBaseUrl)
        .append("/booking/payment-return?gateway=vnpay&status=")
        .append(status);
    if (bookingCode != null && !bookingCode.isBlank()) {
      sb.append("&code=").append(URLEncoder.encode(bookingCode, StandardCharsets.UTF_8));
    }
    return sb.toString();
  }

  private static Map<String, String> rsp(String code, String message) {
    Map<String, String> m = new HashMap<>();
    m.put("RspCode", code);
    m.put("Message", message);
    return m;
  }
}
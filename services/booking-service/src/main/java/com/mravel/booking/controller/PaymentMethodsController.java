package com.mravel.booking.controller;

import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.*;

import com.mravel.booking.model.Payment;
import com.mravel.booking.payment.gateway.PaymentGatewayRegistry;
import com.mravel.common.response.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentMethodsController {

  private final PaymentGatewayRegistry registry;

  @GetMapping("/methods")
  public ApiResponse<List<PaymentMethodDTO>> methods() {
    // Bạn muốn show nhiều icon nhưng chỉ enable cái nào BE support thật
    var desired = List.of(
        Payment.PaymentMethod.MOMO_WALLET,
        Payment.PaymentMethod.VNPAY,
        Payment.PaymentMethod.ZALOPAY
    );

    Set<Payment.PaymentMethod> supported = registry.availableMethods();

    var items = desired.stream()
        .map(m -> new PaymentMethodDTO(
            m.name(),
            labelOf(m),
            iconKeyOf(m),
            supported.contains(m)
        ))
        .toList();

    return ApiResponse.success("OK", items);
  }

  private static String labelOf(Payment.PaymentMethod m) {
    return switch (m) {
      case MOMO_WALLET -> "MoMo";
      case VNPAY -> "VNPay";
      case ZALOPAY -> "ZaloPay";
      case CASH_AT_HOTEL -> "Tiền mặt tại khách sạn";
      default -> "Khác";
    };
  }

  private static String iconKeyOf(Payment.PaymentMethod m) {
    return switch (m) {
      case MOMO_WALLET -> "momo";
      case VNPAY -> "vnpay";
      case ZALOPAY -> "zalopay";
      case CASH_AT_HOTEL -> "cash";
      default -> "other";
    };
  }

  public record PaymentMethodDTO(
      String method,
      String label,
      String iconKey,
      boolean enabled
  ) {}
}
package com.mravel.booking.payment;

import java.util.Arrays;
import java.util.stream.Collectors;

import com.mravel.booking.model.Payment;

public final class PaymentMethodUtils {

  private PaymentMethodUtils() {}

  public static Payment.PaymentMethod parseOrNull(String raw) {
    if (raw == null) return null;
    String v = raw.trim();
    if (v.isBlank()) return null;

    try {
      return Payment.PaymentMethod.valueOf(v.toUpperCase());
    } catch (IllegalArgumentException ex) {
      throw new IllegalArgumentException("paymentMethod không hợp lệ: " + v
          + ". Allowed: " + allowed());
    }
  }

  public static Payment.PaymentMethod parseOrDefault(String raw, Payment.PaymentMethod def) {
    Payment.PaymentMethod m = parseOrNull(raw);
    return (m != null) ? m : def;
  }

  private static String allowed() {
    return Arrays.stream(Payment.PaymentMethod.values())
        .map(Enum::name)
        .collect(Collectors.joining(", "));
  }
}
package com.mravel.booking.payment.gateway;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.mravel.booking.model.Payment;

@Component
public class PaymentGatewayRegistry {

  private final Map<Payment.PaymentMethod, PaymentGateway> map =
      new EnumMap<>(Payment.PaymentMethod.class);

  public PaymentGatewayRegistry(List<PaymentGateway> gateways) {
    for (PaymentGateway g : gateways) {
      map.put(g.method(), g);
    }
  }

  public PaymentGateway require(Payment.PaymentMethod method) {
    PaymentGateway g = map.get(method);
    if (g == null) throw new IllegalStateException("Chưa cấu hình gateway cho method=" + method);
    return g;
  }

  /** Các method có gateway bean thật sự */
  public Set<Payment.PaymentMethod> availableMethods() {
    return Collections.unmodifiableSet(map.keySet());
  }

  public boolean supports(Payment.PaymentMethod method) {
    return map.containsKey(method);
  }
}
package com.mravel.booking.payment.gateway;

import java.math.BigDecimal;

import com.mravel.booking.model.BookingBase;
import com.mravel.booking.model.Payment;

public interface PaymentGateway {

  Payment.PaymentMethod method();

  /** Tạo payUrl + providerRequestId (orderId) cho attempt */
  GatewayCreateResult createPayment(GatewayCreateRequest req);

  record GatewayCreateRequest(
      BookingBase booking,
      String attemptId,
      BigDecimal amount,
      String orderInfo
  ) {}

  record GatewayCreateResult(
      String provider,      // "MOMO", "VNPAY", "ZALOPAY"...
      String providerRequestId,
      String payUrl,
      String rawResponse     // optional
  ) {}
}
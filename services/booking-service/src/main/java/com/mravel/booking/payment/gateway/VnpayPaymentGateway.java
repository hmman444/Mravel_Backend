// src/main/java/com/mravel/booking/payment/gateway/VnpayPaymentGateway.java
package com.mravel.booking.payment.gateway;

import org.springframework.stereotype.Component;

import com.mravel.booking.model.Payment;
import com.mravel.booking.payment.vnpay.VnpayGatewayClient;

@Component
public class VnpayPaymentGateway implements PaymentGateway {

  private final VnpayGatewayClient vnpay;

  public VnpayPaymentGateway(VnpayGatewayClient vnpay) {
    this.vnpay = vnpay;
  }

  @Override
  public Payment.PaymentMethod method() {
    return Payment.PaymentMethod.VNPAY;
  }

  @Override
  public GatewayCreateResult createPayment(GatewayCreateRequest req) {
    // attemptId bạn đang dùng rất hợp làm vnp_TxnRef (unique theo ngày là đẹp)
    String payUrl = vnpay.buildPayUrl(
        req.attemptId(),
        req.amount(),
        req.orderInfo(),
        "127.0.0.1"
    );
    return new GatewayCreateResult("VNPAY", req.attemptId(), payUrl, null);
  }
}
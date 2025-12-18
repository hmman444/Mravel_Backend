package com.mravel.booking.payment.gateway;

import org.springframework.stereotype.Component;

import com.mravel.booking.model.Payment;
import com.mravel.booking.payment.momo.MomoGatewayClient;

@Component
public class MomoPaymentGateway implements PaymentGateway {

  private final MomoGatewayClient momo;

  public MomoPaymentGateway(MomoGatewayClient momo) {
    this.momo = momo;
  }

  @Override
  public Payment.PaymentMethod method() {
    return Payment.PaymentMethod.MOMO_WALLET;
  }

  @Override
  public GatewayCreateResult createPayment(GatewayCreateRequest req) {
    String payUrl = momo.createPayment(req.attemptId(), req.amount(), req.orderInfo());
    return new GatewayCreateResult("MOMO", req.attemptId(), payUrl, null);
  }
}
package com.mravel.booking.service;

import com.mravel.booking.model.BookingBase;
import com.mravel.booking.model.Payment;
import com.mravel.booking.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mravel.booking.payment.gateway.PaymentGatewayRegistry;
import com.mravel.booking.payment.gateway.PaymentGateway;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PaymentAttemptService {

  private final PaymentRepository paymentRepo;
  private final PaymentGatewayRegistry registry;

  @Transactional
  public Payment createOrReusePendingAttempt(
      BookingBase booking,
      Payment.PaymentMethod method,
      Instant expiresAt
  ) {
    if (booking == null) throw new IllegalArgumentException("booking null");
    if (method == null) throw new IllegalArgumentException("method null");

    Instant now = Instant.now();
    if (expiresAt != null && now.isAfter(expiresAt)) {
      throw new IllegalStateException("Đơn đã quá hạn thanh toán");
    }

    // 1) reuse attempt PENDING gần nhất nếu cùng method + còn hạn + có payUrl
    List<Payment> pendings = paymentRepo.findByBookingAndStatusOrderByCreatedAtDesc(
        booking, Payment.PaymentStatus.PENDING
    );

    for (Payment p : pendings) {
      boolean sameMethod = (p.getMethod() == method);
      boolean hasUrl = (p.getProviderPayUrl() != null && !p.getProviderPayUrl().isBlank());
      boolean notExpired = (p.getExpiresAt() == null) || now.isBefore(p.getExpiresAt());

      if (sameMethod && hasUrl && notExpired) {
        return p;
      }
    }

    // 2) cancel tất cả pending cũ (để đổi method không bị “lẫn attempt”)
    if (!pendings.isEmpty()) {
      for (Payment p : pendings) {
        if (p.getStatus() == Payment.PaymentStatus.PENDING) {
          p.setStatus(Payment.PaymentStatus.CANCELLED);
        }
      }
      paymentRepo.saveAll(pendings);
    }

    // 3) tạo attempt mới
    String attemptId = booking.getCode() + "-" + UUID.randomUUID().toString()
        .replace("-", "").substring(0, 6).toUpperCase();

    PaymentGateway gateway = registry.require(method);

    String orderInfo = "Thanh toan " + booking.getCode();
    var result = gateway.createPayment(new PaymentGateway.GatewayCreateRequest(
        booking, attemptId, booking.getAmountPayable(), orderInfo
    ));

    Payment attempt = Payment.builder()
        .booking(booking)
        .method(method)
        .status(Payment.PaymentStatus.PENDING)
        .amount(booking.getAmountPayable())
        .currencyCode(booking.getCurrencyCode())
        .provider(result.provider())
        .providerRequestId(result.providerRequestId())
        .providerPayUrl(result.payUrl())
        .providerRawResponse(result.rawResponse())
        .expiresAt(expiresAt)
        .build();

    return paymentRepo.save(Objects.requireNonNull(attempt, "attempt must not be null"));
  }
}
// src/main/java/com/mravel/booking/service/VnpayPaymentService.java
package com.mravel.booking.service;

import com.mravel.booking.dto.PaymentReturnResult;
import com.mravel.booking.model.BookingBase;
import com.mravel.booking.model.Payment;
import com.mravel.booking.payment.vnpay.VnpayGatewayClient;
import com.mravel.booking.repository.PaymentRepository;
import com.mravel.booking.repository.RestaurantBookingRepository;
import com.mravel.booking.repository.HotelBookingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class VnpayPaymentService {

  private final VnpayGatewayClient vnpayGatewayClient;
  private final PaymentRepository paymentRepository;

  private final HotelBookingService hotelBookingService;
  private final RestaurantBookingService restaurantBookingService;

  // fallback cũ (nếu cần)
  private final HotelBookingRepository hotelBookingRepository;
  private final RestaurantBookingRepository restaurantBookingRepository;

  @Transactional
  public void handleIpn(Map<String, String> params) {
    if (params == null || params.isEmpty())
      throw new IllegalArgumentException("empty params");
    if (!vnpayGatewayClient.verifySignature(params))
      throw new IllegalArgumentException("bad signature");

    String txnRef = trim(params.get("vnp_TxnRef"));
    if (txnRef == null)
      throw new IllegalArgumentException("missing vnp_TxnRef");

    String responseCode = trim(params.get("vnp_ResponseCode")); // "00" = success :contentReference[oaicite:6]{index=6}
    String transNo = trim(params.get("vnp_TransactionNo"));
    BigDecimal paidAmount = parseAmountVnd(params.get("vnp_Amount")); // vnp_Amount đang *100

    if ("00".equals(responseCode)) {
      resolveAndMarkPaid(txnRef, paidAmount, transNo);
      return;
    }

    // fail: nếu muốn, có thể mark PaymentAttempt FAILED
    var p = paymentRepository.findByProviderRequestId(txnRef).orElse(null);
    if (p != null) {
      p.setStatus(Payment.PaymentStatus.FAILED);
      p.setProviderTransactionId(transNo);
      paymentRepository.save(p);
    }
  }

  @Transactional
  public PaymentReturnResult handleReturn(Map<String, String> params) {
    if (params == null || params.isEmpty())
      return PaymentReturnResult.none();
    if (!vnpayGatewayClient.verifySignature(params))
      return PaymentReturnResult.none();

    String txnRef = trim(params.get("vnp_TxnRef"));
    if (txnRef == null)
      return PaymentReturnResult.none();

    String responseCode = trim(params.get("vnp_ResponseCode"));
    String txnStatus = trim(params.get("vnp_TransactionStatus"));
    String transNo = trim(params.get("vnp_TransactionNo"));
    BigDecimal paidAmount = parseAmountVnd(params.get("vnp_Amount")); // vnp_Amount * 100

    if ("00".equals(responseCode) && "00".equals(txnStatus)) {
      String bookingCode = resolveAndMarkPaid(txnRef, paidAmount, transNo);
      return new PaymentReturnResult(bookingCode, true);
    } else {
      // thất bại: mark PaymentAttempt FAILED và trả về code (nếu tìm được) để FE hiển thị
      var p = paymentRepository.findByProviderRequestId(txnRef).orElse(null);
      if (p != null) {
        p.setStatus(Payment.PaymentStatus.FAILED);
        p.setProviderTransactionId(transNo);
        paymentRepository.save(p);
      }
      return PaymentReturnResult.failed(findBookingCode(txnRef));
    }
  }

  // == core ==

  private String resolveAndMarkPaid(String providerRequestId, BigDecimal paidAmount, String providerTxnId) {
    // 0) ưu tiên PaymentAttempt (Payment) trước (chuẩn theo code bạn)
    var p = paymentRepository.findByProviderRequestId(providerRequestId).orElse(null);
    if (p != null && p.getBooking() != null) {
      BookingBase booking = p.getBooking();

      p.setStatus(Payment.PaymentStatus.SUCCESS);
      p.setPaidAt(Instant.now());
      p.setProviderTransactionId(providerTxnId);
      paymentRepository.save(p);

      if (booking instanceof com.mravel.booking.model.HotelBooking hb) {
        hotelBookingService.markHotelBookingPaidAndConfirm(hb.getCode(), paidAmount);
        return hb.getCode();
      }
      if (booking instanceof com.mravel.booking.model.RestaurantBooking rb) {
        // RestaurantBookingService đang nhận Long amount
        Long amtLong = (paidAmount == null) ? null : paidAmount.longValue();
        restaurantBookingService.markRestaurantBookingPaidAndConfirm(rb.getCode(), amtLong);
        return rb.getCode();
      }
    }

    // 1) fallback tương thích cũ (providerRequestId == BK-/RB-)
    var hb = hotelBookingRepository.findByCode(providerRequestId).orElse(null);
    if (hb != null) {
      hotelBookingService.markHotelBookingPaidAndConfirm(hb.getCode(), paidAmount);
      return hb.getCode();
    }

    var rb = restaurantBookingRepository.findByCode(providerRequestId).orElse(null);
    if (rb != null) {
      Long amtLong = (paidAmount == null) ? null : paidAmount.longValue();
      restaurantBookingService.markRestaurantBookingPaidAndConfirm(rb.getCode(), amtLong);
      return rb.getCode();
    }

    throw new IllegalArgumentException("Không tìm thấy booking theo providerRequestId=" + providerRequestId);
  }

  /** Tìm bookingCode từ providerRequestId mà KHÔNG đánh dấu paid (dùng cho nhánh thất bại). */
  private String findBookingCode(String providerRequestId) {
    var p = paymentRepository.findByProviderRequestId(providerRequestId).orElse(null);
    if (p != null && p.getBooking() != null) {
      return p.getBooking().getCode();
    }
    var hb = hotelBookingRepository.findByCode(providerRequestId).orElse(null);
    if (hb != null) {
      return hb.getCode();
    }
    var rb = restaurantBookingRepository.findByCode(providerRequestId).orElse(null);
    if (rb != null) {
      return rb.getCode();
    }
    return null;
  }

  private static BigDecimal parseAmountVnd(String vnpAmount) {
    // VNPay gửi amount * 100 :contentReference[oaicite:7]{index=7}
    String s = trim(vnpAmount);
    if (s == null)
      return null;
    try {
      long raw = Long.parseLong(s);
      return BigDecimal.valueOf(raw).divide(BigDecimal.valueOf(100));
    } catch (Exception e) {
      log.warn("Invalid VNPay amount: {}", vnpAmount);
      throw new IllegalArgumentException("Invalid VNPay amount: " + vnpAmount);
    }
  }

  private static String trim(String s) {
    if (s == null)
      return null;
    String t = s.trim();
    return t.isBlank() ? null : t;
  }
}
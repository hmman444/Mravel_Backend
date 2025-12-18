package com.mravel.booking.payment.vnpay;

import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class VnpayGatewayClient {

  private static final DateTimeFormatter VNP_DT =
      DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

  private final VnpayProperties props;

  public VnpayGatewayClient(VnpayProperties props) {
    this.props = Objects.requireNonNull(props, "props null");
  }

  /** Build payUrl để redirect user qua VNPay */
  public String buildPayUrl(String txnRef, BigDecimal amountVnd, String orderInfo, String clientIp) {
    Objects.requireNonNull(txnRef, "txnRef null");
    Objects.requireNonNull(amountVnd, "amountVnd null");

    String safeOrderInfo = (orderInfo == null || orderInfo.isBlank())
        ? ("Thanh toan " + txnRef)
        : orderInfo;

    // VNPay: vnp_Amount = amount * 100
    long vnpAmount = amountVnd.setScale(0, RoundingMode.HALF_UP).longValueExact() * 100L;

    ZoneId vn = ZoneId.of("Asia/Ho_Chi_Minh");
    LocalDateTime now = LocalDateTime.now(vn);

    int expireMin = (props.payExpireMinutes() == null || props.payExpireMinutes() <= 0)
        ? 30 : props.payExpireMinutes();
    LocalDateTime exp = now.plusMinutes(expireMin);

    SortedMap<String, String> vnp = new TreeMap<>();
    vnp.put("vnp_Version", nvl(props.version(), "2.1.0"));
    vnp.put("vnp_Command", nvl(props.command(), "pay"));
    vnp.put("vnp_TmnCode", props.tmnCode());
    vnp.put("vnp_Amount", String.valueOf(vnpAmount));
    vnp.put("vnp_CurrCode", nvl(props.currCode(), "VND"));
    vnp.put("vnp_TxnRef", txnRef);
    vnp.put("vnp_OrderInfo", safeOrderInfo);
    vnp.put("vnp_OrderType", nvl(props.orderType(), "other"));
    vnp.put("vnp_Locale", nvl(props.locale(), "vn"));
    vnp.put("vnp_ReturnUrl", props.returnUrl());
    vnp.put("vnp_IpAddr", (clientIp == null || clientIp.isBlank()) ? "127.0.0.1" : clientIp);
    vnp.put("vnp_CreateDate", now.format(VNP_DT));
    vnp.put("vnp_ExpireDate", exp.format(VNP_DT));

    // 1) hashData: fieldName=URLEncoder.encode(fieldValue) (fieldName KHÔNG encode)
    String hashData = buildHashData(vnp);

    // 2) query: encode cả key và value
    String query = buildQuery(vnp);

    String secureHash = hmacSHA512(props.hashSecret(), hashData);

    // VNPay demo thường chỉ cần vnp_SecureHash, secureHashType có thể để hoặc bỏ
    return props.baseUrl()
        + "?"
        + query
        + "&vnp_SecureHashType=HmacSHA512"
        + "&vnp_SecureHash=" + secureHash;
  }

  /** Verify chữ ký VNPay trả về (ReturnURL/IPN) */
  public boolean verifySignature(Map<String, String> allParams) {
    if (allParams == null || allParams.isEmpty()) return false;

    String receivedHash = trimToNull(allParams.get("vnp_SecureHash"));
    if (receivedHash == null) return false;

    SortedMap<String, String> vnp = new TreeMap<>();
    for (var e : allParams.entrySet()) {
      String k = e.getKey();
      String v = e.getValue();
      if (k == null) continue;
      if (!k.startsWith("vnp_")) continue;
      if ("vnp_SecureHash".equals(k) || "vnp_SecureHashType".equals(k)) continue;
      if (v == null || v.isBlank()) continue;
      vnp.put(k, v);
    }

    String hashData = buildHashData(vnp);
    String expected = hmacSHA512(props.hashSecret(), hashData);

    return expected.equalsIgnoreCase(receivedHash);
  }

  private static String buildHashData(SortedMap<String, String> params) {
    StringBuilder sb = new StringBuilder();
    boolean first = true;

    for (var e : params.entrySet()) {
      String k = e.getKey();
      String v = e.getValue();
      if (v == null || v.isBlank()) continue;

      if (!first) sb.append('&');
      first = false;

      sb.append(k).append('=').append(urlEncode(v));
    }
    return sb.toString();
  }

  private static String buildQuery(SortedMap<String, String> params) {
    StringBuilder sb = new StringBuilder();
    boolean first = true;

    for (var e : params.entrySet()) {
      String k = e.getKey();
      String v = e.getValue();
      if (v == null || v.isBlank()) continue;

      if (!first) sb.append('&');
      first = false;

      sb.append(urlEncode(k)).append('=').append(urlEncode(v));
    }
    return sb.toString();
  }

  private static String urlEncode(String s) {
    try {
      // VNPay demo code dùng US_ASCII; UTF-8 vẫn OK nếu dữ liệu bạn chủ yếu ASCII
      return URLEncoder.encode(s, StandardCharsets.US_ASCII.toString());
    } catch (Exception e) {
      throw new RuntimeException("urlEncode error", e);
    }
  }

  private static String hmacSHA512(String secretKey, String data) {
    try {
      Mac hmac = Mac.getInstance("HmacSHA512");
      SecretKeySpec keySpec =
          new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
      hmac.init(keySpec);

      byte[] hash = hmac.doFinal(data.getBytes(StandardCharsets.UTF_8));
      StringBuilder sb = new StringBuilder(hash.length * 2);
      for (byte b : hash) sb.append(String.format("%02x", b & 0xff));
      return sb.toString();
    } catch (Exception e) {
      throw new RuntimeException("VNPay HMACSHA512 error", e);
    }
  }

  private static String nvl(String v, String def) {
    return (v == null || v.isBlank()) ? def : v;
  }

  private static String trimToNull(String s) {
    if (s == null) return null;
    String t = s.trim();
    return t.isBlank() ? null : t;
  }
}
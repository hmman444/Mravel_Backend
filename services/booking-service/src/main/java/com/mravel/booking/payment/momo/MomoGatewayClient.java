package com.mravel.booking.payment.momo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.HttpStatusCodeException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class MomoGatewayClient {

    private final RestTemplate restTemplate;

    // --- SANDBOX CONFIG (demo) ---
    private static final String ENDPOINT = "https://test-payment.momo.vn/v2/gateway/api/create";
    private static final String PARTNER_CODE = "MOMO";
    private static final String ACCESS_KEY = "F8BBA842ECF85";
    private static final String SECRET_KEY = "K951B6PE1waDMi640xX08PD3vg6EkVlz";
    private static final String REQUEST_TYPE = "captureWallet";

    // NOTE: nên đưa vào application.yml/env sau
    private static final String REDIRECT_URL = "http://localhost:8084/api/payment/momo/redirect";
    private static final String IPN_URL = "http://localhost:8084/api/payment/momo/ipn";

    /**
     * Tạo giao dịch MoMo cho 1 booking và trả về payUrl.
     */
    public String createPayment(String orderId, BigDecimal amount, String orderInfo) {
        if (orderId == null || orderId.isBlank()) {
            throw new IllegalArgumentException("orderId không hợp lệ");
        }
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount không hợp lệ");
        }

        long amountLong = amount.setScale(0, RoundingMode.HALF_UP).longValueExact();
        String amountStr = String.valueOf(amountLong);

        String requestId = orderId + "-" + System.currentTimeMillis();

        String extraData = "";

        if (orderInfo == null || orderInfo.isBlank()) {
            orderInfo = "Thanh toan " + orderId;
        }

        String rawSignature =
            "accessKey=" + ACCESS_KEY +
            "&amount=" + amountStr +
            "&extraData=" + extraData +
            "&ipnUrl=" + IPN_URL +
            "&orderId=" + orderId +
            "&orderInfo=" + orderInfo +
            "&partnerCode=" + PARTNER_CODE +
            "&redirectUrl=" + REDIRECT_URL +
            "&requestId=" + requestId +
            "&requestType=" + REQUEST_TYPE;

        String signature = hmacSHA256(SECRET_KEY, rawSignature);

        Map<String, Object> body = new HashMap<>();
        body.put("partnerCode", PARTNER_CODE);
        body.put("accessKey", ACCESS_KEY);
        body.put("partnerName", "Mravel");
        body.put("storeId", "MravelStore");
        body.put("requestId", requestId);
        body.put("amount", amountLong);
        body.put("orderId", orderId);
        body.put("orderInfo", orderInfo);
        body.put("redirectUrl", REDIRECT_URL);
        body.put("ipnUrl", IPN_URL);
        body.put("lang", "vi");
        body.put("requestType", REQUEST_TYPE);
        body.put("autoCapture", true);
        body.put("extraData", extraData);
        body.put("signature", signature);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        log.info("[MoMo] createPayment request: orderId={}, amount={}", orderId, amountStr);

         try {
            ResponseEntity<Map<String, Object>> resp = restTemplate.exchange(
                ENDPOINT,
                HttpMethod.POST,
                new HttpEntity<>(body, headers),
                new ParameterizedTypeReference<Map<String, Object>>() {}
            );

            Map<String, Object> respBody = Objects.requireNonNull(resp.getBody(), "MoMo response body is null");

            int resultCode = asInt(respBody.get("resultCode"), -999);
            Object message = respBody.get("message");
            log.info("[MoMo] response resultCode={}, message={}", resultCode, message);

            if (resultCode != 0) {
                throw new IllegalStateException("MoMo từ chối giao dịch: " + message);
            }

            String payUrl = Objects.toString(respBody.get("payUrl"), null);
            if (payUrl == null || payUrl.isBlank()) {
                throw new IllegalStateException("MoMo trả về payUrl rỗng");
            }
            return payUrl;

        } catch (HttpStatusCodeException ex) {
            // ✅ MoMo trả 4xx/5xx → đọc được body JSON ở đây
            String momoBody = ex.getResponseBodyAsString();
            log.error("[MoMo] error status={}, body={}", ex.getStatusCode(), momoBody);
            throw new IllegalStateException("MoMo declined: " + momoBody, ex);
        }
    }

    private static int asInt(Object obj, int defaultVal) {
        if (obj == null) return defaultVal;
        if (obj instanceof Number n) return n.intValue();
        try {
            return Integer.parseInt(String.valueOf(obj));
        } catch (Exception ignore) {
            return defaultVal;
        }
    }

    private static String hmacSHA256(String secretKey, String data) {
        try {
            Mac hmac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec =
                    new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            hmac.init(secretKeySpec);

            byte[] hash = hmac.doFinal(data.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder(2 * hash.length);
            for (byte b : hash) sb.append(String.format("%02x", b & 0xff));
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi generate HMAC SHA256", e);
        }
    }
}
// src/main/java/com/mravel/booking/payment/MomoPaymentService.java
package com.mravel.booking.payment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class MomoGatewayClient {

    private final RestTemplate restTemplate;

    // --- SANDBOX CONFIG (giống project MERN cũ) ---
    private static final String ENDPOINT = "https://test-payment.momo.vn/v2/gateway/api/create";
    private static final String PARTNER_CODE = "MOMO";
    private static final String ACCESS_KEY = "F8BBA842ECF85";
    private static final String SECRET_KEY = "K951B6PE1waDMi640xX08PD3vg6EkVlz";
    private static final String REQUEST_TYPE = "captureWallet";

    // TODO: sửa cho đúng FE & BE của bạn
    private static final String REDIRECT_URL = "http://localhost:8084/api/payment/momo/redirect";
    private static final String IPN_URL = "http://localhost:8084/api/payment/momo/ipn";

    /**
     * Tạo giao dịch MoMo cho 1 booking và trả về payUrl.
     * @param bookingCode mã booking (BK-XXXX)
     * @param amount      số tiền cần thanh toán (VND)
     * @param hotelName   tên khách sạn (để show trong orderInfo cho đẹp)
     */
    public String createPayment(String bookingCode, BigDecimal amount, String hotelName) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount không hợp lệ");
        }

        // Momo yêu cầu amount là số nguyên VND dạng string
        String amountStr = amount.setScale(0, RoundingMode.HALF_UP).toPlainString();

        String orderId   = bookingCode;
        String requestId = bookingCode; // giống Node: requestId = orderId
        String orderInfo = "Thanh toan dat phong " + (hotelName != null ? hotelName : bookingCode);
        String extraData = "";

        // rawSignature giống hệt Node:
        // accessKey=<ACCESS_KEY>&amount=<AMOUNT>&extraData=<EXTRA_DATA>&ipnUrl=<IPN_URL>
        // &orderId=<ORDER_ID>&orderInfo=<ORDER_INFO>&partnerCode=<PARTNER_CODE>
        // &redirectUrl=<REDIRECT_URL>&requestId=<REQUEST_ID>&requestType=<REQUEST_TYPE>
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
        body.put("partnerName", "Mravel");
        body.put("storeId", "MravelStore");
        body.put("requestId", requestId);
        body.put("amount", amountStr);
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
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        log.info("[MoMo] createPayment request: orderId={}, amount={}", orderId, amountStr);

        ResponseEntity<Map> resp = restTemplate.postForEntity(ENDPOINT, entity, Map.class);
        if (!resp.getStatusCode().is2xxSuccessful() || resp.getBody() == null) {
            throw new IllegalStateException("MoMo create payment thất bại: " + resp.getStatusCode());
        }

        Map<String, Object> respBody = resp.getBody();
        Object resultCodeObj = respBody.get("resultCode");
        int resultCode = (resultCodeObj instanceof Number)
                ? ((Number) resultCodeObj).intValue()
                : Integer.parseInt(String.valueOf(resultCodeObj));

        log.info("[MoMo] response resultCode={}, message={}", resultCode, respBody.get("message"));

        if (resultCode != 0) {
            throw new IllegalStateException("MoMo từ chối giao dịch: " + respBody.get("message"));
        }

        String payUrl = (String) respBody.get("payUrl");
        if (payUrl == null || payUrl.isBlank()) {
            throw new IllegalStateException("MoMo trả về payUrl rỗng");
        }

        return payUrl;
    }

    private String hmacSHA256(String secretKey, String data) {
        try {
            Mac hmac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec =
                    new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            hmac.init(secretKeySpec);
            byte[] hash = hmac.doFinal(data.getBytes(StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi generate HMAC SHA256", e);
        }
    }
}
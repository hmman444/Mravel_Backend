package com.mravel.realtime.presence;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Kiểm tra chặn (2 chiều) để không lộ trạng thái online giữa cặp đã chặn nhau.
 * Fail-open: lỗi -> không lọc.
 */
@Slf4j
@Component
public class PresenceBlockGuard {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${mravel.services.user.base-url}")
    private String userBaseUrl;

    @Value("${mravel.internal.secret:}")
    private String internalSecret;

    public boolean isBlocked(Long a, Long b) {
        if (a == null || b == null || a.equals(b))
            return false;
        try {
            String url = userBaseUrl + "/api/users/internal/block-check?a=" + a + "&b=" + b;
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-Internal-Secret", internalSecret);
            ResponseEntity<Map> res = restTemplate.exchange(
                    url, HttpMethod.GET, new HttpEntity<>(headers), Map.class);
            Map<?, ?> body = res.getBody();
            return body != null && Boolean.TRUE.equals(body.get("data"));
        } catch (Exception e) {
            log.debug("presence block-check failed: {}", e.getMessage());
            return false;
        }
    }
}

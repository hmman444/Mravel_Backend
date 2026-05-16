package com.mravel.catalog.client;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Client gọi auth-service để validate JWT và lấy userId.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class AuthServiceClient {

    private final RestTemplate restTemplate;

    @Value("${mravel.services.auth.base-url}")
    private String authBaseUrl;

    @Value("${mravel.services.auth.validate-path}")
    private String validatePath;

    /**
     * Validate bearer token và trả về userId.
     * 
     * @param bearer chuỗi "Bearer xxx" hoặc "xxx"
     * @return userId nếu token hợp lệ, null nếu không hợp lệ hoặc lỗi
     */
    public Long extractUserId(String bearer) {
        if (bearer == null || bearer.isBlank()) {
            return null;
        }

        try {
            HttpHeaders headers = new HttpHeaders();
            // Đảm bảo header luôn có prefix "Bearer "
            String normalized = bearer.trim();
            if (!normalized.toLowerCase().startsWith("bearer ")) {
                normalized = "Bearer " + normalized;
            }
            headers.set("Authorization", normalized);

            HttpEntity<Void> entity = new HttpEntity<>(headers);

            ResponseEntity<Map<String, Object>> resp = restTemplate.exchange(
                    authBaseUrl + validatePath,
                    HttpMethod.GET,
                    entity,
                    new org.springframework.core.ParameterizedTypeReference<Map<String, Object>>() {}
            );

            Map<String, Object> body = resp.getBody();
            if (body == null) return null;

            Object valid = body.get("valid");
            if (valid == null || !(Boolean) valid) return null;

            Object id = body.get("id");
            if (id == null) return null;

            return (id instanceof Number) ? ((Number) id).longValue() : Long.valueOf(String.valueOf(id));
        } catch (Exception e) {
            log.warn("Không thể validate token qua auth-service: {}", e.getMessage());
            return null;
        }
    }
}

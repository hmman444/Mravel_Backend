package com.mravel.review.client;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Gọi plan-service để biết user (theo token) đã trải nghiệm dịch vụ chưa.
 * Forward nguyên Authorization header để plan-service tự xác thực + lấy userId
 * (tránh giả mạo userId).
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PlanClient {

    private final RestTemplate restTemplate;

    @Value("${mravel.services.plan.base-url}")
    private String planBaseUrl;

    public boolean isExperienced(String targetType, String targetId, String slug, String name) {
        try {
            String url = UriComponentsBuilder
                    .fromHttpUrl(planBaseUrl + "/api/plans/internal/experienced")
                    .queryParam("targetType", targetType)
                    .queryParam("targetId", targetId)
                    .queryParamIfPresent("slug", Optional.ofNullable(emptyToNull(slug)))
                    .queryParamIfPresent("name", Optional.ofNullable(emptyToNull(name)))
                    .toUriString();

            HttpHeaders headers = new HttpHeaders();
            String auth = currentAuthHeader();
            if (auth != null) headers.set(HttpHeaders.AUTHORIZATION, auth);

            ResponseEntity<Map> res = restTemplate.exchange(
                    url, HttpMethod.GET, new HttpEntity<>(headers), Map.class);

            Object data = res.getBody() != null ? res.getBody().get("data") : null;
            if (data instanceof Map<?, ?> m) {
                return Boolean.TRUE.equals(m.get("eligible"));
            }
            return false;
        } catch (Exception ex) {
            // Fail-closed: không xác minh được thì CHẶN (ưu tiên tính toàn vẹn của đánh giá).
            log.warn("isExperienced check failed for {} {}: {}", targetType, targetId, ex.getMessage());
            return false;
        }
    }

    private static String emptyToNull(String s) {
        return (s == null || s.isBlank()) ? null : s;
    }

    private String currentAuthHeader() {
        if (RequestContextHolder.getRequestAttributes() instanceof ServletRequestAttributes sra) {
            return sra.getRequest().getHeader(HttpHeaders.AUTHORIZATION);
        }
        return null;
    }
}

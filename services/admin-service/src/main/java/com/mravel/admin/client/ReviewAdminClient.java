package com.mravel.admin.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mravel.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

/**
 * Proxy tới review-service cho thao tác kiểm duyệt đánh giá (admin).
 * Forward Bearer token của admin; review-service tự kiểm tra vai trò ADMIN trong code.
 */
@Component
@RequiredArgsConstructor
public class ReviewAdminClient {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${mravel.services.review.base-url}")
    private String baseUrl;

    @Value("${spring.profiles.active:}")
    private String activeProfile;

    private static final String ADMIN_BASE = "/api/reviews/admin";

    public ResponseEntity<ApiResponse<?>> listNegative(String targetType, String targetId, int maxRating,
            int page, int size, String bearer) {
        UriComponentsBuilder b = UriComponentsBuilder
                .fromHttpUrl(requireBaseUrl() + ADMIN_BASE + "/negative")
                .queryParam("targetType", targetType)
                .queryParam("maxRating", maxRating)
                .queryParam("page", page)
                .queryParam("size", size);
        if (targetId != null && !targetId.isBlank())
            b.queryParam("targetId", targetId);
        return exchange(b.toUriString(), HttpMethod.GET, null, bearer);
    }

    public ResponseEntity<ApiResponse<?>> negativeCount(String targetType, int maxRating, String bearer) {
        String url = UriComponentsBuilder
                .fromHttpUrl(requireBaseUrl() + ADMIN_BASE + "/negative/count")
                .queryParam("targetType", targetType)
                .queryParam("maxRating", maxRating)
                .toUriString();
        return exchange(url, HttpMethod.GET, null, bearer);
    }

    public ResponseEntity<ApiResponse<?>> delete(Long reviewId, String bearer) {
        String url = requireBaseUrl() + ADMIN_BASE + "/" + reviewId;
        return exchange(url, HttpMethod.DELETE, null, bearer);
    }

    private ResponseEntity<ApiResponse<?>> exchange(String url, HttpMethod method, Object body, String bearer) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(normalize(bearer));
        headers.setAccept(java.util.List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<>(body, headers);
        try {
            ResponseEntity<ApiResponse<?>> resp = restTemplate.exchange(
                    url, method, entity, new ParameterizedTypeReference<ApiResponse<?>>() {
                    });
            return ResponseEntity.status(resp.getStatusCode()).body(resp.getBody());
        } catch (HttpStatusCodeException ex) {
            ApiResponse<?> api;
            try {
                api = objectMapper.readValue(ex.getResponseBodyAsString(), ApiResponse.class);
            } catch (Exception ignore) {
                api = ApiResponse.error(ex.getStatusText());
            }
            return ResponseEntity.status(ex.getStatusCode()).body(api);
        }
    }

    private String requireBaseUrl() {
        String base = Objects.requireNonNull(baseUrl, "mravel.services.review.base-url must not be null");
        if (activeProfile != null && activeProfile.contains("docker") && base.contains("localhost")) {
            base = base.replaceAll("localhost(:\\d+)?", "gateway:8080");
        }
        return base;
    }

    private String normalize(String bearerOrToken) {
        if (bearerOrToken == null)
            return "";
        String s = bearerOrToken.trim();
        return s.regionMatches(true, 0, "Bearer ", 0, 7) ? s.substring(7).trim() : s;
    }
}

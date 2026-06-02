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
 * Proxy tới plan-service cho các thao tác kiểm duyệt + thống kê (admin).
 * Forward Bearer token của admin; plan-service tự kiểm tra hasRole('ADMIN').
 */
@Component
@RequiredArgsConstructor
public class PlanAdminClient {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${mravel.services.plan.base-url}")
    private String baseUrl;

    @Value("${spring.profiles.active:}")
    private String activeProfile;

    private static final String ADMIN_BASE = "/api/plans/internal/admin";

    public ResponseEntity<ApiResponse<?>> stats(int days, String bearer) {
        String url = UriComponentsBuilder.fromHttpUrl(requireBaseUrl() + ADMIN_BASE + "/stats")
                .queryParam("days", days).toUriString();
        return exchange(url, HttpMethod.GET, null, bearer);
    }

    /** Số report plan đang PENDING (cho action-queue). Fail-soft: 0 nếu lỗi. */
    public long pendingReports(String bearer) {
        try {
            ResponseEntity<ApiResponse<?>> resp = stats(7, bearer);
            ApiResponse<?> body = resp.getBody();
            if (body == null || !body.isSuccess() || body.getData() == null)
                return 0L;
            com.fasterxml.jackson.databind.JsonNode node = objectMapper.valueToTree(body.getData());
            return node.path("overview").path("pendingReports").asLong(0L);
        } catch (Exception ex) {
            return 0L;
        }
    }

    public ResponseEntity<ApiResponse<?>> listReports(String status, int page, int size, String bearer) {
        UriComponentsBuilder b = UriComponentsBuilder
                .fromHttpUrl(requireBaseUrl() + ADMIN_BASE + "/reports")
                .queryParam("page", page).queryParam("size", size);
        if (status != null && !status.isBlank())
            b.queryParam("status", status);
        return exchange(b.toUriString(), HttpMethod.GET, null, bearer);
    }

    public ResponseEntity<ApiResponse<?>> takedown(Long id, String reason, String bearer) {
        UriComponentsBuilder b = UriComponentsBuilder
                .fromHttpUrl(requireBaseUrl() + ADMIN_BASE + "/plans/" + id + "/takedown");
        if (reason != null && !reason.isBlank())
            b.queryParam("reason", reason);
        return exchange(b.toUriString(), HttpMethod.PATCH, null, bearer);
    }

    public ResponseEntity<ApiResponse<?>> resolveReport(Long id, String action, String reason, String bearer) {
        UriComponentsBuilder b = UriComponentsBuilder
                .fromHttpUrl(requireBaseUrl() + ADMIN_BASE + "/reports/" + id + "/resolve")
                .queryParam("action", action);
        if (reason != null && !reason.isBlank())
            b.queryParam("reason", reason);
        return exchange(b.toUriString(), HttpMethod.POST, null, bearer);
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
        String base = Objects.requireNonNull(baseUrl, "mravel.services.plan.base-url must not be null");
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

package com.mravel.admin.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mravel.admin.dto.dashboard.DownstreamDtos.BookingStats;
import com.mravel.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Objects;

/**
 * Proxy tới booking-service cho số liệu booking của admin dashboard.
 * Forward Bearer token; booking-service tự xác thực role ADMIN qua auth-service.
 * Fail-soft: trả null khi lỗi để dashboard vẫn dựng được phần còn lại.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class BookingAdminClient {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${mravel.services.booking.base-url}")
    private String baseUrl;

    @Value("${spring.profiles.active:}")
    private String activeProfile;

    public BookingStats getStats(String range, String bearer) {
        try {
            String url = UriComponentsBuilder.fromHttpUrl(requireBaseUrl() + "/api/booking/admin/dashboard")
                    .queryParam("range", range == null || range.isBlank() ? "weekly" : range)
                    .toUriString();

            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(normalize(bearer));
            headers.setAccept(List.of(MediaType.APPLICATION_JSON));

            ResponseEntity<ApiResponse<?>> resp = restTemplate.exchange(
                    url, HttpMethod.GET, new HttpEntity<>(headers),
                    new ParameterizedTypeReference<ApiResponse<?>>() {
                    });

            ApiResponse<?> body = resp.getBody();
            if (body == null || !body.isSuccess() || body.getData() == null)
                return null;
            return objectMapper.convertValue(body.getData(), BookingStats.class);
        } catch (Exception ex) {
            log.warn("[BookingAdminClient] getStats failed: {}", ex.getMessage());
            return null;
        }
    }

    private String requireBaseUrl() {
        String base = Objects.requireNonNull(baseUrl, "mravel.services.booking.base-url must not be null");
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

package com.mravel.review.client;

import java.util.Map;

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
 * Hỏi booking-service: user (theo token) đã trải nghiệm dịch vụ qua BOOKING chưa
 * (đặt phòng/đặt bàn đã qua mốc sử dụng). Forward Bearer token để booking-service
 * tự lấy userId từ JWT.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class BookingClient {

    private final RestTemplate restTemplate;

    @Value("${mravel.services.booking.base-url}")
    private String bookingBaseUrl;

    public boolean hasUsedBooking(String targetType, String targetId) {
        try {
            String url = UriComponentsBuilder
                    .fromHttpUrl(bookingBaseUrl + "/api/booking/internal/experienced")
                    .queryParam("targetType", targetType)
                    .queryParam("targetId", targetId)
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
            // Fail-safe: lỗi -> coi như chưa trải nghiệm qua booking (vẫn còn nhánh plan).
            log.warn("hasUsedBooking check failed for {} {}: {}", targetType, targetId, ex.getMessage());
            return false;
        }
    }

    private String currentAuthHeader() {
        if (RequestContextHolder.getRequestAttributes() instanceof ServletRequestAttributes sra) {
            return sra.getRequest().getHeader(HttpHeaders.AUTHORIZATION);
        }
        return null;
    }
}

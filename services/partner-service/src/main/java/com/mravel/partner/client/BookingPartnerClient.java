package com.mravel.partner.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mravel.common.response.ApiResponse;
import com.mravel.partner.dto.PartnerDtos;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookingPartnerClient {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${mravel.services.booking.base-url}")
    private String baseUrl;

    // ----------------- LIST / DETAIL -----------------

    public ResponseEntity<ApiResponse<?>> listHotelBookings(String status, Integer page, Integer size, String bearer) {
        String url = UriComponentsBuilder
                .fromHttpUrl(baseUrl + "/api/booking/partners/hotels")
                .queryParamIfPresent("status", (status == null || status.isBlank()) ? Optional.empty() : Optional.of(status))
                .queryParamIfPresent("page", Optional.ofNullable(page))
                .queryParamIfPresent("size", Optional.ofNullable(size))
                .toUriString();
        return exchangeAbsolute(url, HttpMethod.GET, null, bearer);
    }

    public ResponseEntity<ApiResponse<?>> getHotelBookingDetail(String bookingCode, String bearer) {
        String url = UriComponentsBuilder
                .fromHttpUrl(baseUrl + "/api/booking/partners/hotels/" + bookingCode)
                .toUriString();
        return exchangeAbsolute(url, HttpMethod.GET, null, bearer);
    }

    public ResponseEntity<ApiResponse<?>> listRestaurantBookings(String status, Integer page, Integer size, String bearer) {
        String url = UriComponentsBuilder
                .fromHttpUrl(baseUrl + "/api/booking/partners/restaurants")
                .queryParamIfPresent("status", (status == null || status.isBlank()) ? Optional.empty() : Optional.of(status))
                .queryParamIfPresent("page", Optional.ofNullable(page))
                .queryParamIfPresent("size", Optional.ofNullable(size))
                .toUriString();
        return exchangeAbsolute(url, HttpMethod.GET, null, bearer);
    }

    public ResponseEntity<ApiResponse<?>> getRestaurantBookingDetail(String bookingCode, String bearer) {
        String url = UriComponentsBuilder
                .fromHttpUrl(baseUrl + "/api/booking/partners/restaurants/" + bookingCode)
                .toUriString();
        return exchangeAbsolute(url, HttpMethod.GET, null, bearer);
    }

    // ----------------- CANCEL -----------------

    public ResponseEntity<ApiResponse<?>> cancelHotelBooking(String bookingCode, PartnerDtos.CancelBookingReq req, String bearer) {
        // JSON body: { "reason": "..." }
        return exchangeAbsolute(baseUrl + "/api/booking/partners/hotels/" + bookingCode + "/cancel",
                HttpMethod.POST, req, bearer);
    }

    public ResponseEntity<ApiResponse<?>> cancelRestaurantBooking(String bookingCode, PartnerDtos.CancelBookingReq req, String bearer) {
        // JSON body: { "reason": "..." }
        return exchangeAbsolute(baseUrl + "/api/booking/partners/restaurants/" + bookingCode + "/cancel",
                HttpMethod.POST, req, bearer);
    }

    // ----------------- STATS -----------------

    public ResponseEntity<ApiResponse<?>> statsByStatus(LocalDate from, LocalDate to, String bearer) {
        String url = UriComponentsBuilder
                .fromHttpUrl(baseUrl + "/api/booking/partners/stats/status")
                .queryParamIfPresent("from", Optional.ofNullable(from))
                .queryParamIfPresent("to", Optional.ofNullable(to))
                .toUriString();
        return exchangeAbsolute(url, HttpMethod.GET, null, bearer);
    }

    public ResponseEntity<ApiResponse<?>> revenue(LocalDate from, LocalDate to, String group, String bearer) {
        String url = UriComponentsBuilder
                .fromHttpUrl(baseUrl + "/api/booking/partners/stats/revenue")
                .queryParamIfPresent("from", Optional.ofNullable(from))
                .queryParamIfPresent("to", Optional.ofNullable(to))
                .queryParamIfPresent("group", (group == null || group.isBlank()) ? Optional.empty() : Optional.of(group))
                .toUriString();
        return exchangeAbsolute(url, HttpMethod.GET, null, bearer);
    }

    // ----------------- shared helpers -----------------

    private ResponseEntity<ApiResponse<?>> exchangeAbsolute(String url, HttpMethod method, Object body, String bearerToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(extractBearer(bearerToken));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> entity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<ApiResponse> resp = restTemplate.exchange(url, method, entity, ApiResponse.class);
            return ResponseEntity.status(resp.getStatusCode()).body(resp.getBody());
        } catch (HttpStatusCodeException ex) {
            ApiResponse<?> api;
            try {
                String raw = ex.getResponseBodyAsString();
                api = objectMapper.readValue(raw, ApiResponse.class);
            } catch (Exception parseErr) {
                api = ApiResponse.error(ex.getStatusText());
            }
            return ResponseEntity.status(ex.getStatusCode()).body(api);
        }
    }

    private String extractBearer(String authorizationHeader) {
        if (authorizationHeader == null) return "";
        String s = authorizationHeader.trim();
        if (s.toLowerCase().startsWith("bearer ")) return s.substring(7).trim();
        return s;
    }
}
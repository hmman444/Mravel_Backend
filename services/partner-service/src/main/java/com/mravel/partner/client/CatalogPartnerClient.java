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

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CatalogPartnerClient {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${mravel.services.catalog.base-url}")
    private String baseUrl;

    // ----------------- HOTEL -----------------

    public ResponseEntity<ApiResponse<?>> listMyHotels(Long partnerId, String status, Integer page, Integer size, String bearer) {
        String url = UriComponentsBuilder
                .fromHttpUrl(baseUrl + "/api/catalog/partners/hotels")
                .queryParam("partnerId", partnerId)
                .queryParamIfPresent("status", (status == null || status.isBlank()) ? Optional.empty() : Optional.of(status))
                .queryParamIfPresent("page", Optional.ofNullable(page))
                .queryParamIfPresent("size", Optional.ofNullable(size))
                .toUriString();
        return exchangeAbsolute(url, HttpMethod.GET, null, bearer);
    }

    public ResponseEntity<ApiResponse<?>> createHotel(Long partnerId, PartnerDtos.UpsertHotelReq req, String bearer) {
        // partnerId lấy từ JWT, gửi kèm để catalog-service set publisher.partnerId
        var body = new CatalogUpsertWrapper<>(partnerId, PartnerDtos.PendingReason.CREATE, req);
        return exchange("/api/catalog/partners/hotels", HttpMethod.POST, body, bearer);
    }

    public ResponseEntity<ApiResponse<?>> updateHotel(String id, Long partnerId, PartnerDtos.UpsertHotelReq req, String bearer) {
        var body = new CatalogUpsertWrapper<>(partnerId, PartnerDtos.PendingReason.UPDATE, req);
        return exchange("/api/catalog/partners/hotels/" + id, HttpMethod.PUT, body, bearer);
    }

    public ResponseEntity<ApiResponse<?>> softDeleteHotel(String id, Long partnerId, String bearer) {
        String url = UriComponentsBuilder
                .fromHttpUrl(baseUrl + "/api/catalog/partners/hotels/" + id)
                .queryParam("partnerId", partnerId)
                .toUriString();
        return exchangeAbsolute(url, HttpMethod.DELETE, null, bearer);
    }

    public ResponseEntity<ApiResponse<?>> pauseHotel(String id, Long partnerId, String bearer) {
        return exchange("/api/catalog/partners/hotels/" + id + "/pause?partnerId=" + partnerId, HttpMethod.POST, null, bearer);
    }

    public ResponseEntity<ApiResponse<?>> resumeHotel(String id, Long partnerId, String bearer) {
        return exchange("/api/catalog/partners/hotels/" + id + "/resume?partnerId=" + partnerId, HttpMethod.POST, null, bearer);
    }

    public ResponseEntity<ApiResponse<?>> requestUnlockHotel(String id, Long partnerId, PartnerDtos.UnlockRequestReq req, String bearer) {
        var body = new UnlockWrapper(partnerId, req.reason());
        return exchange("/api/catalog/partners/hotels/" + id + "/unlock-request", HttpMethod.POST, body, bearer);
    }

    // ----------------- RESTAURANT -----------------

    public ResponseEntity<ApiResponse<?>> listMyRestaurants(Long partnerId, String status, Integer page, Integer size, String bearer) {
        String url = UriComponentsBuilder
                .fromHttpUrl(baseUrl + "/api/catalog/partners/restaurants")
                .queryParam("partnerId", partnerId)
                .queryParamIfPresent("status", (status == null || status.isBlank()) ? Optional.empty() : Optional.of(status))
                .queryParamIfPresent("page", Optional.ofNullable(page))
                .queryParamIfPresent("size", Optional.ofNullable(size))
                .toUriString();
        return exchangeAbsolute(url, HttpMethod.GET, null, bearer);
    }

    public ResponseEntity<ApiResponse<?>> createRestaurant(Long partnerId, PartnerDtos.UpsertRestaurantReq req, String bearer) {
        var body = new CatalogUpsertWrapper<>(partnerId, PartnerDtos.PendingReason.CREATE, req);
        return exchange("/api/catalog/partners/restaurants", HttpMethod.POST, body, bearer);
    }

    public ResponseEntity<ApiResponse<?>> updateRestaurant(String id, Long partnerId, PartnerDtos.UpsertRestaurantReq req, String bearer) {
        var body = new CatalogUpsertWrapper<>(partnerId, PartnerDtos.PendingReason.UPDATE, req);
        return exchange("/api/catalog/partners/restaurants/" + id, HttpMethod.PUT, body, bearer);
    }

    public ResponseEntity<ApiResponse<?>> softDeleteRestaurant(String id, Long partnerId, String bearer) {
        String url = UriComponentsBuilder
                .fromHttpUrl(baseUrl + "/api/catalog/partners/restaurants/" + id)
                .queryParam("partnerId", partnerId)
                .toUriString();
        return exchangeAbsolute(url, HttpMethod.DELETE, null, bearer);
    }

    public ResponseEntity<ApiResponse<?>> pauseRestaurant(String id, Long partnerId, String bearer) {
        return exchange("/api/catalog/partners/restaurants/" + id + "/pause?partnerId=" + partnerId, HttpMethod.POST, null, bearer);
    }

    public ResponseEntity<ApiResponse<?>> resumeRestaurant(String id, Long partnerId, String bearer) {
        return exchange("/api/catalog/partners/restaurants/" + id + "/resume?partnerId=" + partnerId, HttpMethod.POST, null, bearer);
    }

    public ResponseEntity<ApiResponse<?>> requestUnlockRestaurant(String id, Long partnerId, PartnerDtos.UnlockRequestReq req, String bearer) {
        var body = new UnlockWrapper(partnerId, req.reason());
        return exchange("/api/catalog/partners/restaurants/" + id + "/unlock-request", HttpMethod.POST, body, bearer);
    }

    // ----------------- shared helpers -----------------

    private ResponseEntity<ApiResponse<?>> exchange(String path, HttpMethod method, Object body, String bearerToken) {
        return exchangeAbsolute(baseUrl + path, method, body, bearerToken);
    }

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

    // Wrapper giúp catalog-service phân biệt pending do tạo mới hay update
    public record CatalogUpsertWrapper<T>(Long partnerId, PartnerDtos.PendingReason pendingReason, T payload) {}
    public record UnlockWrapper(Long partnerId, String reason) {}
}
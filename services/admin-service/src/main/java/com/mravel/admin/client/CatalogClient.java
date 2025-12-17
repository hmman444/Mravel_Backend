package com.mravel.admin.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mravel.admin.dto.amenity.AmenityUpsertRequest;
import com.mravel.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class CatalogClient {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${mravel.services.catalog.base-url}")
    private String baseUrl;

    public ResponseEntity<ApiResponse<?>> createAmenity(AmenityUpsertRequest req, String bearerToken) {
        return exchange("/api/catalog/amenities", HttpMethod.POST, req, bearerToken);
    }

    public ResponseEntity<ApiResponse<?>> updateAmenity(String id, AmenityUpsertRequest req, String bearerToken) {
        return exchange("/api/catalog/amenities/" + id, HttpMethod.PUT, req, bearerToken);
    }

    public ResponseEntity<ApiResponse<?>> deleteAmenity(String id, String bearerToken) {
        return exchange("/api/catalog/amenities/" + id, HttpMethod.DELETE, null, bearerToken);
    }

    public ResponseEntity<ApiResponse<?>> listAmenities(String scope, boolean active, boolean grouped,
            String bearerToken) {
        String url = UriComponentsBuilder
                .fromHttpUrl(baseUrl + "/api/catalog/amenities")
                .queryParam("active", active)
                .queryParam("grouped", grouped)
                .queryParamIfPresent("scope",
                        (scope == null || scope.isBlank())
                                ? Optional.empty()
                                : Optional.of(scope))
                .toUriString();

        return exchangeAbsolute(url, HttpMethod.GET, null, bearerToken);
    }

    private ResponseEntity<ApiResponse<?>> exchange(String path, HttpMethod method, Object body, String bearerToken) {
        return exchangeAbsolute(baseUrl + path, method, body, bearerToken);
    }

    private ResponseEntity<ApiResponse<?>> exchangeAbsolute(String url, HttpMethod method, Object body,
            String bearerToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(bearerToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> entity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<ApiResponse> resp = restTemplate.exchange(url, method, entity, ApiResponse.class);
            return ResponseEntity.status(resp.getStatusCode()).body(resp.getBody());
        } catch (HttpStatusCodeException ex) {
            // Catalog trả 4xx/5xx => parse body ApiResponse để lấy message chuẩn
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
}

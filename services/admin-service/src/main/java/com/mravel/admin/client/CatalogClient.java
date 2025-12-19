package com.mravel.admin.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mravel.admin.dto.amenity.AmenityUpsertRequest;
import com.mravel.admin.dto.place.PlaceAdminDtos.UpsertPlaceRequest;
import com.mravel.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CatalogClient {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${mravel.services.catalog.base-url}")
    private String baseUrl;

    // AMENITY
    public ResponseEntity<ApiResponse<?>> createAmenity(AmenityUpsertRequest req, String bearerToken) {
        return exchange("/api/catalog/amenities", HttpMethod.POST, req, bearerToken);
    }

    public ResponseEntity<ApiResponse<?>> updateAmenity(String id, AmenityUpsertRequest req, String bearerToken) {
        return exchange("/api/catalog/amenities/" + id, HttpMethod.PUT, req, bearerToken);
    }

    public ResponseEntity<ApiResponse<?>> deleteAmenity(String id, String bearerToken) {
        return exchange("/api/catalog/amenities/" + id, HttpMethod.DELETE, null, bearerToken);
    }

    public ResponseEntity<ApiResponse<?>> listAmenities(String scope, boolean active, boolean grouped, String bearerToken) {
        UriComponentsBuilder b = UriComponentsBuilder
                .fromHttpUrl(requireBaseUrl() + "/api/catalog/amenities")
                .queryParam("active", active)
                .queryParam("grouped", grouped);

        if (scope != null && !scope.isBlank()) {
            b.queryParam("scope", scope);
        }

        return exchangeAbsolute(b.toUriString(), HttpMethod.GET, null, bearerToken);
    }

    // PLACE
    public ResponseEntity<ApiResponse<?>> searchPlaces(String q, Integer page, Integer size, String bearerToken) {
        UriComponentsBuilder b = UriComponentsBuilder
                .fromHttpUrl(requireBaseUrl() + "/api/catalog/places/poi");

        if (q != null && !q.isBlank()) b.queryParam("q", q);
        if (page != null) b.queryParam("page", page);
        if (size != null) b.queryParam("size", size);

        return exchangeAbsolute(b.toUriString(), HttpMethod.GET, null, bearerToken);
    }

    public ResponseEntity<ApiResponse<?>> getPlaceDetailBySlug(String slug, String bearerToken) {
        return exchange("/api/catalog/places/" + slug, HttpMethod.GET, null, bearerToken);
    }

    public ResponseEntity<ApiResponse<?>> getChildrenByParentSlug(
            String slug, String kind, Integer page, Integer size, String bearerToken
    ) {
        UriComponentsBuilder b = UriComponentsBuilder
                .fromHttpUrl(requireBaseUrl() + "/api/catalog/places/" + slug + "/children");

        if (kind != null && !kind.isBlank()) b.queryParam("kind", kind);
        if (page != null) b.queryParam("page", page);
        if (size != null) b.queryParam("size", size);

        return exchangeAbsolute(b.toUriString(), HttpMethod.GET, null, bearerToken);
    }

    public ResponseEntity<ApiResponse<?>> createPlace(UpsertPlaceRequest req, String bearerToken) {
        return exchange("/api/catalog/places", HttpMethod.POST, req, bearerToken);
    }

    public ResponseEntity<ApiResponse<?>> updatePlace(String id, UpsertPlaceRequest req, String bearerToken) {
        return exchange("/api/catalog/places/" + id, HttpMethod.PUT, req, bearerToken);
    }

    public ResponseEntity<ApiResponse<?>> softDeletePlace(String id, String bearerToken) {
        return exchange("/api/catalog/places/" + id, HttpMethod.DELETE, null, bearerToken);
    }

    // ===== common exchange helpers =====
    private ResponseEntity<ApiResponse<?>> exchange(String path, HttpMethod method, Object body, String bearerToken) {
        String url = requireBaseUrl() + Objects.requireNonNull(path, "path must not be null");
        return exchangeAbsolute(url, method, body, bearerToken);
    }

    private ResponseEntity<ApiResponse<?>> exchangeAbsolute(String url, HttpMethod method, Object body, String bearerToken) {
        final String safeUrl = Objects.requireNonNull(url, "url must not be null");
        final HttpMethod safeMethod = Objects.requireNonNull(method, "method must not be null");
        final String safeToken = Objects.requireNonNull(bearerToken, "bearerToken must not be null");

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(safeToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> entity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<ApiResponse<?>> resp = restTemplate.exchange(
                    safeUrl,
                    safeMethod,
                    entity,
                    new org.springframework.core.ParameterizedTypeReference<ApiResponse<?>>() {}
            );
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

    private String requireBaseUrl() {
        return Objects.requireNonNull(baseUrl, "mravel.services.catalog.base-url must not be null");
    }
}
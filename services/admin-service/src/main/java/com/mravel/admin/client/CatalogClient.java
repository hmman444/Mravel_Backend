package com.mravel.admin.client;

import com.mravel.admin.dto.amenity.AmenityUpsertRequest;
import com.mravel.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class CatalogClient {

    private final RestTemplate restTemplate;

    @Value("${mravel.services.catalog.base-url}")
    private String baseUrl;

    public ApiResponse<?> createAmenity(AmenityUpsertRequest req, String bearerToken) {
        return exchange("/api/amenities", HttpMethod.POST, req, bearerToken);
    }

    public ApiResponse<?> updateAmenity(String id, AmenityUpsertRequest req, String bearerToken) {
        return exchange("/api/amenities/" + id, HttpMethod.PUT, req, bearerToken);
    }

    public ApiResponse<?> deleteAmenity(String id, String bearerToken) {
        return exchange("/api/amenities/" + id, HttpMethod.DELETE, null, bearerToken);
    }

    public ApiResponse<?> listAmenities(String scope, boolean active, boolean grouped, String bearerToken) {
        String url = UriComponentsBuilder
                .fromHttpUrl(baseUrl + "/api/amenities")
                .queryParam("active", active)
                .queryParam("grouped", grouped)
                .queryParamIfPresent("scope",
                        (scope == null || scope.isBlank()) ? java.util.Optional.empty() : java.util.Optional.of(scope))
                .toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(bearerToken);

        ResponseEntity<ApiResponse> resp = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                ApiResponse.class);
        return resp.getBody();
    }

    private ApiResponse<?> exchange(String path, HttpMethod method, Object body, String bearerToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(bearerToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> entity = new HttpEntity<>(body, headers);

        ResponseEntity<ApiResponse> resp = restTemplate.exchange(
                baseUrl + path,
                method,
                entity,
                ApiResponse.class);

        return resp.getBody();
    }
}

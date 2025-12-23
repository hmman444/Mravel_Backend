// src/main/java/com/mravel/admin/client/AuthAdminClient.java
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

@Component
@RequiredArgsConstructor
public class AuthAdminClient {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${mravel.services.auth.base-url}")
    private String baseUrl;

    public ResponseEntity<ApiResponse<?>> listUsers(String role, String q, Integer page, Integer size,
            String bearerToken) {
        String url = UriComponentsBuilder
                .fromHttpUrl(requireBaseUrl() + "/api/auth/users")
                .queryParam("role", role)
                .queryParam("q", q == null ? "" : q)
                .queryParam("page", page == null ? 0 : page)
                .queryParam("size", size == null ? 20 : size)
                .toUriString();

        return exchangeAbsolute(url, HttpMethod.GET, null, bearerToken);
    }

    public ResponseEntity<ApiResponse<?>> lock(Long id, String bearerToken) {
        return exchange("/api/auth/users/" + id + "/lock", HttpMethod.PATCH, null, bearerToken);
    }

    public ResponseEntity<ApiResponse<?>> unlock(Long id, String bearerToken) {
        return exchange("/api/auth/users/" + id + "/unlock", HttpMethod.PATCH, null, bearerToken);
    }

    private ResponseEntity<ApiResponse<?>> exchange(String path, HttpMethod method, Object body, String bearerToken) {
        String url = requireBaseUrl() + Objects.requireNonNull(path);
        return exchangeAbsolute(url, method, body, bearerToken);
    }

    private ResponseEntity<ApiResponse<?>> exchangeAbsolute(String url, HttpMethod method, Object body,
            String bearerToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.setBearerAuth(Objects.requireNonNull(bearerToken));

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
            } catch (Exception parseErr) {
                api = ApiResponse.error(ex.getStatusText());
            }
            return ResponseEntity.status(ex.getStatusCode()).body(api);
        }
    }

    private String requireBaseUrl() {
        return Objects.requireNonNull(baseUrl, "mravel.services.auth.base-url must not be null");
    }
}

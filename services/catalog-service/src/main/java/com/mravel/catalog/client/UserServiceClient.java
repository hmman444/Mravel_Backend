package com.mravel.catalog.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class UserServiceClient {

    private final RestTemplate restTemplate;

    @Value("${mravel.services.user.base-url}")
    private String baseUrl;

    public UserProfileResponse getUserById(Long id, String bearer) {
        String safeBaseUrl = Objects.requireNonNull(baseUrl, "mravel.services.user.base-url must not be null");
        Long safeId = Objects.requireNonNull(id, "id must not be null");

        String url = safeBaseUrl + "/api/users/" + safeId;

        HttpHeaders headers = new HttpHeaders();
        String token = normalizeBearerToken(bearer);
        if (token != null && !token.isBlank()) {
            headers.setBearerAuth(token);
        }

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<UserProfileResponse> resp = restTemplate.exchange(
                url,
                Objects.requireNonNull(HttpMethod.GET, "HttpMethod must not be null"),
                entity,
                UserProfileResponse.class
        );

        return resp.getBody();
    }

    /**
     * Accepts:
     * - "Bearer xxx"
     * - "bearer xxx"
     * - "xxx"
     * - null
     * Returns: raw token (without "Bearer "), or null if input null/blank.
     */
    private static String normalizeBearerToken(String bearer) {
        if (bearer == null) return null;
        String b = bearer.trim();
        if (b.isBlank()) return null;

        String lower = b.toLowerCase();
        if (lower.startsWith("bearer ")) {
            return b.substring(7).trim();
        }
        return b;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record UserProfileResponse(
            Long id,
            String email,
            String fullname,
            String role
    ) {}
}
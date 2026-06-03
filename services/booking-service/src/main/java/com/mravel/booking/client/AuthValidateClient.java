package com.mravel.booking.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class AuthValidateClient {

    private final RestTemplate restTemplate;

    @Value("${mravel.services.auth.base-url}")
    private String authBaseUrl;

    @Value("${mravel.services.auth.validate-path:/api/auth/validate}")
    private String validatePath;

    public Long requirePartnerId(String authorizationHeader) {
        return requireRole(authorizationHeader, "PARTNER");
    }

    /** Validate token and require ADMIN role; returns the admin user id. */
    public Long requireAdminId(String authorizationHeader) {
        return requireRole(authorizationHeader, "ADMIN");
    }

    private Long requireRole(String authorizationHeader, String requiredRole) {
        URI uri = UriComponentsBuilder.fromHttpUrl(authBaseUrl)
                .path(validatePath)
                .build(true)
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(extractBearer(authorizationHeader));

        try {
            ResponseEntity<AuthResponse> resp = restTemplate.exchange(
                    uri,
                    HttpMethod.GET,
                    new HttpEntity<>(null, headers),
                    AuthResponse.class);

            AuthResponse body = resp.getBody();
            if (body == null || !body.isValid())
                throw new SecurityException("Invalid token");

            if (body.getRole() == null || !requiredRole.equalsIgnoreCase(body.getRole())) {
                throw new SecurityException("Not " + requiredRole.toLowerCase());
            }

            if (body.getId() == null)
                throw new SecurityException("Invalid token");
            return body.getId();

        } catch (org.springframework.web.client.HttpStatusCodeException ex) {
            throw new SecurityException("Invalid token");
        }
    }

    private String extractBearer(String authorizationHeader) {
        if (authorizationHeader == null)
            return "";
        String s = authorizationHeader.trim();
        if (s.toLowerCase().startsWith("bearer "))
            return s.substring(7).trim();
        return s;
    }

    @lombok.Data
    public static class AuthResponse {
        private boolean valid;
        private Long id;
        private String email;
        private String role;
    }
}
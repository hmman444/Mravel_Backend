package com.mravel.catalog.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class UserServiceClient {

    private final RestTemplate restTemplate;

    @Value("${mravel.services.user.base-url}")
    private String baseUrl;

    public UserProfileResponse getUserById(Long id, String bearer) {
        String url = baseUrl + "/api/users/" + id;

        HttpHeaders headers = new HttpHeaders();
        if (bearer != null && bearer.toLowerCase().startsWith("bearer ")) {
            headers.setBearerAuth(bearer.substring(7).trim());
        } else if (bearer != null) {
            headers.setBearerAuth(bearer.trim());
        }

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<UserProfileResponse> resp =
                restTemplate.exchange(url, HttpMethod.GET, entity, UserProfileResponse.class);

        return resp.getBody();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record UserProfileResponse(
            Long id,
            String email,
            String fullname,
            String role
    ) {}
}
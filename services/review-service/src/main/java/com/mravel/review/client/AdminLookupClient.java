package com.mravel.review.client;

import com.mravel.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Fetches admin user ids from auth-service so review notifications can fan out
 * to admins. Fail-silent: returns an empty list on any error.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AdminLookupClient {

    private final RestTemplate restTemplate;

    @Value("${mravel.services.auth.base-url}")
    private String authBaseUrl;

    public List<Long> getAdminIds() {
        try {
            ApiResponse<List<Long>> body = restTemplate.exchange(
                    authBaseUrl + "/api/auth/internal/admin-ids",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<ApiResponse<List<Long>>>() {
                    }).getBody();
            if (body == null || body.getData() == null)
                return List.of();
            return body.getData();
        } catch (Exception e) {
            log.warn("[AdminLookupClient] admin-ids lookup failed: {}", e.getMessage());
            return List.of();
        }
    }
}

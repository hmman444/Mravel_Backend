package com.mravel.plan.client;

import com.mravel.common.response.UserProfileResponse;
import lombok.RequiredArgsConstructor;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.net.URLEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class UserProfileClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${mravel.services.user.base-url}")
    private String userBaseUrl;

    @Value("${mravel.services.user.userProfiles-path}")
    private String userApiPath;

    private String userServiceUrl() {
        return userBaseUrl + userApiPath;
    }

    @Cacheable(value = "userProfiles", key = "#userId")
    public UserProfileResponse getUserById(Long userId) {
        String url = String.format("%s/%d", userServiceUrl(), userId);
        ResponseEntity<UserProfileResponse> res = restTemplate.getForEntity(url, UserProfileResponse.class);
        return res.getBody();
    }

    public List<UserProfileResponse> searchUsers(String authorizationHeader, String q, int limit) {
        String keyword = q == null ? "" : q.trim();
        String encoded = URLEncoder.encode(keyword, StandardCharsets.UTF_8);
        String url = userServiceUrl() + "/search?q=" + encoded + "&limit=" + limit;

        HttpHeaders headers = new HttpHeaders();
        if (authorizationHeader != null && !authorizationHeader.isBlank()) {
            headers.set("Authorization", authorizationHeader);
        }

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<UserProfileResponse[]> res = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                UserProfileResponse[].class);

        UserProfileResponse[] body = res.getBody();
        return body == null ? List.of() : List.of(body);
    }
}

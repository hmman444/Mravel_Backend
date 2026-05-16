package com.mravel.review.client;

import com.mravel.common.response.UserProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class UserProfileClient {

    private final RestTemplate restTemplate;

    @Value("${mravel.services.user.base-url}")
    private String userBaseUrl;

    @Value("${mravel.services.user.userProfiles-path}")
    private String userApiPath;

    private String userServiceUrl() {
        return userBaseUrl + userApiPath;
    }

    public UserProfileResponse getUserById(Long userId) {
        String url = String.format("%s/%d", userServiceUrl(), userId);
        ResponseEntity<UserProfileResponse> res = restTemplate.getForEntity(url, UserProfileResponse.class);
        return res.getBody();
    }
}

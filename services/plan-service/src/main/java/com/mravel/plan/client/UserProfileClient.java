package com.mravel.plan.client;

import com.mravel.common.response.UserProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class UserProfileClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${user.service.url:http://localhost:8082/api/users}")
    private String userServiceUrl;

    @Cacheable(value = "userProfiles", key = "#userId")
    public UserProfileResponse getUserById(Long userId) {
        String url = String.format("%s/%d", userServiceUrl, userId);
        ResponseEntity<UserProfileResponse> res = restTemplate.getForEntity(url, UserProfileResponse.class);
        return res.getBody();
    }
}

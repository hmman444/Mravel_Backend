package com.mravel.plan.client;

import com.mravel.common.response.ApiResponse;
import com.mravel.common.response.RelationshipType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class FriendClient {

    // giữ như bạn đang làm (deadline): tự tạo RestTemplate
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${mravel.services.user.base-url}")
    private String userBaseUrl;

    @Value("${mravel.services.user.userProfiles-path}")
    private String userApiPath;

    private String userServiceUrl() {
        return Objects.requireNonNull(userBaseUrl, "userBaseUrl must not be null")
                + Objects.requireNonNull(userApiPath, "userApiPath must not be null");
    }

    public RelationshipType getRelationship(String authorizationHeader, Long profileUserId) {
        String url = userServiceUrl() + "/friends/relationship?profileUserId=" + profileUserId;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorizationHeader);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<ApiResponse<?>> res = restTemplate.exchange(
                url,
                Objects.requireNonNull(HttpMethod.GET),
                entity,
                new ParameterizedTypeReference<ApiResponse<?>>() {}
        );

        ApiResponse<?> body = res.getBody();
        Object data = (body == null) ? null : body.getData();

        return (data != null)
                ? RelationshipType.valueOf(String.valueOf(data))
                : RelationshipType.NONE;
    }

    public List<Long> getFriendIds(String authorizationHeader) {
        String url = userServiceUrl() + "/friends/ids";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorizationHeader);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<ApiResponse<?>> res = restTemplate.exchange(
                url,
                Objects.requireNonNull(HttpMethod.GET),
                entity,
                new ParameterizedTypeReference<ApiResponse<?>>() {}
        );

        ApiResponse<?> body = res.getBody();
        Object data = (body == null) ? null : body.getData();
        if (data == null) return List.of();

        if (!(data instanceof List<?> raw)) return List.of();

        List<Long> ids = new ArrayList<>(raw.size());
        for (Object o : raw) {
            if (o != null) ids.add(Long.valueOf(o.toString()));
        }
        return ids;
    }
}
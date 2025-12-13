package com.mravel.plan.client;

import com.mravel.common.response.ApiResponse;
import com.mravel.common.response.RelationshipType;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class FriendClient {

        private final RestTemplate restTemplate = new RestTemplate();

        @Value("${user.service.url:http://localhost:8082/api/users}")
        private String userServiceUrl;

        public RelationshipType getRelationship(
                        String authorizationHeader,
                        Long profileUserId) {
                String url = userServiceUrl + "/friends/relationship?profileUserId=" + profileUserId;

                HttpHeaders headers = new HttpHeaders();
                headers.set("Authorization", authorizationHeader);

                HttpEntity<Void> entity = new HttpEntity<>(headers);

                ResponseEntity<ApiResponse> res = restTemplate.exchange(
                                url,
                                HttpMethod.GET,
                                entity,
                                ApiResponse.class);

                Object data = res.getBody().getData();
                return data != null
                                ? RelationshipType.valueOf(data.toString())
                                : RelationshipType.NONE;
        }

        public List<Long> getFriendIds(String authorizationHeader) {
                String url = userServiceUrl + "/friends/ids";

                HttpHeaders headers = new HttpHeaders();
                headers.set("Authorization", authorizationHeader);
                HttpEntity<Void> entity = new HttpEntity<>(headers);

                ResponseEntity<ApiResponse> res = restTemplate.exchange(
                                url,
                                HttpMethod.GET,
                                entity,
                                ApiResponse.class);

                Object data = res.getBody().getData();
                if (data == null)
                        return List.of();

                List<?> raw = (List<?>) data;
                List<Long> ids = new ArrayList<>();
                for (Object o : raw)
                        ids.add(Long.valueOf(o.toString()));
                return ids;
        }
}

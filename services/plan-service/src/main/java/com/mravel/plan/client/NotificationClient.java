package com.mravel.plan.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class NotificationClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${mravel.services.notification.base-url}")
    private String baseUrl;

    @Value("${mravel.services.notification.create-path}")
    private String createPath;

    public void createNotification(Long recipientId, Long actorId, String type, String title, String message,
            Map<String, Object> data) {
        String url = baseUrl + createPath;

        Map<String, Object> body = Map.of(
                "recipientId", recipientId,
                "actorId", actorId,
                "type", type,
                "title", title,
                "message", message,
                "data", data);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(body, headers), Object.class);
    }
}

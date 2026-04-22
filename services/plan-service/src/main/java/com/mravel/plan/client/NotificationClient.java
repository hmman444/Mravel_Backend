package com.mravel.plan.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationClient {

    private final RestTemplate restTemplate;

    @Value("${mravel.services.notification.base-url}")
    private String baseUrl;

    @Value("${mravel.services.notification.create-path}")
    private String createPath;

    public void createNotification(Long recipientId, Long actorId, String type, String title, String message,
            Map<String, Object> data) {
        String url = baseUrl + createPath;

        Map<String, Object> body = new HashMap<>();
        body.put("recipientId", recipientId);
        if (actorId != null) body.put("actorId", actorId);
        body.put("type", type);
        body.put("title", title);
        body.put("message", message);
        if (data != null) body.put("data", data);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(body, headers), Object.class);
    }
}

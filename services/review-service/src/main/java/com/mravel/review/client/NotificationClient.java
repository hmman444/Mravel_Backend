package com.mravel.review.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Fail-silent HTTP client for publishing in-app notifications to notification-service.
 * Review operations must never fail because notification-service is unavailable.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationClient {

    private final RestTemplate restTemplate;

    @Value("${mravel.services.notification.base-url}")
    private String baseUrl;

    private static final String CREATE_PATH = "/api/notifications";

    public void createNotification(Long recipientId, Long actorId, String type,
            String title, String message, Map<String, Object> data) {
        if (recipientId == null)
            return;
        try {
            Map<String, Object> body = new HashMap<>();
            body.put("recipientId", recipientId);
            if (actorId != null)
                body.put("actorId", actorId);
            body.put("type", type);
            body.put("title", title);
            body.put("message", message);
            if (data != null)
                body.put("data", data);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            restTemplate.exchange(
                    baseUrl + CREATE_PATH,
                    HttpMethod.POST,
                    new HttpEntity<>(body, headers),
                    Object.class);
        } catch (Exception e) {
            log.warn("[NotificationClient] Failed — type={} recipient={}: {}",
                    type, recipientId, e.getMessage());
        }
    }
}

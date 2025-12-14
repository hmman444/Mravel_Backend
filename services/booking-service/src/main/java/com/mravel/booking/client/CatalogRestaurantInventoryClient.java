// src/main/java/com/mravel/booking/client/CatalogRestaurantInventoryClient.java
package com.mravel.booking.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mravel.common.response.ApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Component
public class CatalogRestaurantInventoryClient {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final String catalogBaseUrl;

    public CatalogRestaurantInventoryClient(
        RestTemplate restTemplate,
        ObjectMapper objectMapper,
        @Value("${mravel.services.catalog.base-url}") String catalogBaseUrl
    ) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.catalogBaseUrl = catalogBaseUrl;
    }

    public void hold(Object req) { post("/api/catalog/restaurants/inventory/hold", req); }
    public void commit(Object req) { post("/api/catalog/restaurants/inventory/commit", req); }
    public void release(Object req) { post("/api/catalog/restaurants/inventory/release", req); }
    public void rollback(Object req) { post("/api/catalog/restaurants/inventory/rollback", req); }
    public void check(Object req) { post("/api/catalog/restaurants/inventory/check", req); }

    private void post(String path, Object req) {
        String url = catalogBaseUrl + path;
        try {
            restTemplate.postForEntity(url, new HttpEntity<>(req), String.class);
        } catch (HttpStatusCodeException ex) {
            throw new IllegalStateException(extractMessage(ex), ex);
        }
    }

    private String extractMessage(HttpStatusCodeException ex) {
        try {
            ApiResponse<?> api = objectMapper.readValue(ex.getResponseBodyAsString(), ApiResponse.class);
            if (api.getMessage() != null && !api.getMessage().isBlank()) return api.getMessage();
        } catch (Exception ignore) {}
        return "Lỗi inventory từ catalog-service: " + ex.getStatusCode();
    }
}
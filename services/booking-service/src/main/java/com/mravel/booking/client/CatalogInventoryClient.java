// src/main/java/com/mravel/booking/client/CatalogInventoryClient.java
package com.mravel.booking.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mravel.booking.client.CatalogInventoryDtos.*;
import com.mravel.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class CatalogInventoryClient {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${mravel.services.catalog.base-url}")
    private String catalogBaseUrl;

    private String buildUrl(String path) {
        return catalogBaseUrl + path;
    }

    public void checkAvailability(CheckInventoryRequest req) {
        String url = buildUrl("/api/catalog/hotels/inventory/check");
        HttpEntity<CheckInventoryRequest> entity = new HttpEntity<>(req);
        try {
            restTemplate.postForEntity(url, entity, String.class);
        } catch (HttpStatusCodeException ex) {
            String message = extractMessage(ex);
            throw new IllegalStateException(message, ex);
        }
    }

    public void deductInventory(DeductInventoryRequest req) {
        String url = buildUrl("/api/catalog/hotels/inventory/deduct");
        HttpEntity<DeductInventoryRequest> entity = new HttpEntity<>(req);
        try {
            restTemplate.postForEntity(url, entity, String.class);
        } catch (HttpStatusCodeException ex) {
            String message = extractMessage(ex);
            throw new IllegalStateException(message, ex);
        }
    }

    public void rollbackInventory(RollbackInventoryRequest req) {
        String url = buildUrl("/api/catalog/hotels/inventory/rollback");
        HttpEntity<RollbackInventoryRequest> entity = new HttpEntity<>(req);
        try {
            restTemplate.postForEntity(url, entity, String.class);
        } catch (HttpStatusCodeException ex) {
            String message = extractMessage(ex);
            throw new IllegalStateException(message, ex);
        }
    }

    private String extractMessage(HttpStatusCodeException ex) {
        try {
            String body = ex.getResponseBodyAsString();
            ApiResponse<?> api = objectMapper.readValue(body, ApiResponse.class);
            if (api.getMessage() != null && !api.getMessage().isBlank()) {
                return api.getMessage();
            }
        } catch (Exception ignore) {}
        // fallback
        return "Lỗi inventory từ catalog-service: " + ex.getStatusCode();
    }

    public void holdInventory(DeductInventoryRequest req) {
        String url = buildUrl("/api/catalog/hotels/inventory/hold");
        HttpEntity<DeductInventoryRequest> entity = new HttpEntity<>(req);
        try {
            restTemplate.postForEntity(url, entity, String.class);
        } catch (HttpStatusCodeException ex) {
            String message = extractMessage(ex);
            throw new IllegalStateException(message, ex);
        }
    }

    public void commitInventory(DeductInventoryRequest req) {
        String url = buildUrl("/api/catalog/hotels/inventory/commit");
        HttpEntity<DeductInventoryRequest> entity = new HttpEntity<>(req);
        try {
            restTemplate.postForEntity(url, entity, String.class);
        } catch (HttpStatusCodeException ex) {
            String message = extractMessage(ex);
            throw new IllegalStateException(message, ex);
        }
    }

    public void releaseHold(RollbackInventoryRequest req) {
        String url = buildUrl("/api/catalog/hotels/inventory/release");
        HttpEntity<RollbackInventoryRequest> entity = new HttpEntity<>(req);
        try {
            restTemplate.postForEntity(url, entity, String.class);
        } catch (HttpStatusCodeException ex) {
            String message = extractMessage(ex);
            throw new IllegalStateException(message, ex);
        }
    }
}
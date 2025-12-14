// src/main/java/com/mravel/booking/client/CatalogInventoryClient.java
package com.mravel.booking.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mravel.booking.client.CatalogInventoryDtos.*;
import com.mravel.common.response.ApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component
public class CatalogInventoryClient {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final String catalogBaseUrl;

    public CatalogInventoryClient(
            RestTemplate restTemplate,
            ObjectMapper objectMapper,
            @Value("${mravel.services.catalog.base-url}") String catalogBaseUrl
    ) {
        this.restTemplate = Objects.requireNonNull(restTemplate, "restTemplate must not be null");
        this.objectMapper = Objects.requireNonNull(objectMapper, "objectMapper must not be null");
        this.catalogBaseUrl = Objects.requireNonNull(catalogBaseUrl, "catalogBaseUrl must not be null");
    }

    public void checkAvailability(CheckInventoryRequest req) {
        post("/api/catalog/hotels/inventory/check", req);
    }

    public void holdInventory(DeductInventoryRequest req) {
        post("/api/catalog/hotels/inventory/hold", req);
    }

    public void commitInventory(DeductInventoryRequest req) {
        post("/api/catalog/hotels/inventory/commit", req);
    }

    public void releaseHold(RollbackInventoryRequest req) {
        post("/api/catalog/hotels/inventory/release", req);
    }

    public void deductInventory(DeductInventoryRequest req) {
        post("/api/catalog/hotels/inventory/deduct", req);
    }

    public void rollbackInventory(RollbackInventoryRequest req) {
        post("/api/catalog/hotels/inventory/rollback", req);
    }

    // ======================= helpers =======================

    private <T> void post(String path, T req) {
        Objects.requireNonNull(path, "path must not be null");
        Objects.requireNonNull(req, "request must not be null");

        String url = buildUrl(path);
        try {
            restTemplate.postForEntity(url, new HttpEntity<>(req), String.class);
        } catch (HttpStatusCodeException ex) {
            throw new IllegalStateException(extractMessage(ex), ex);
        }
    }

    private String buildUrl(String path) {
        return catalogBaseUrl + path;
    }

    private String extractMessage(HttpStatusCodeException ex) {
        try {
            String body = ex.getResponseBodyAsString();
            ApiResponse<?> api = objectMapper.readValue(body, ApiResponse.class);
            String msg = api.getMessage();
            if (msg != null && !msg.isBlank()) return msg;
        } catch (Exception ignore) {
        }
        return "Lỗi inventory từ catalog-service: " + ex.getStatusCode();
    }
}
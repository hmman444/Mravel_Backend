package com.mravel.booking.client;

import com.mravel.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Resolves the owner (partner) + display thumbnail of a hotel/restaurant so the
 * booking-service can notify the partner of new/cancelled bookings.
 *
 * Fail-silent: a booking must never fail because the owner lookup is unavailable.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CatalogOwnerClient {

    private final RestTemplate restTemplate;

    @Value("${mravel.services.catalog.base-url}")
    private String catalogBaseUrl;

    public record OwnerInfo(Long partnerId, String name, String slug, String thumbnailUrl) {
    }

    public OwnerInfo getHotelOwner(String hotelId) {
        if (hotelId == null || hotelId.isBlank()) return null;
        return fetch("/api/catalog/internal/hotels/" + hotelId + "/owner");
    }

    public OwnerInfo getRestaurantOwner(String restaurantId) {
        if (restaurantId == null || restaurantId.isBlank()) return null;
        return fetch("/api/catalog/internal/restaurants/" + restaurantId + "/owner");
    }

    private OwnerInfo fetch(String path) {
        try {
            ApiResponse<Map<String, Object>> body = restTemplate.exchange(
                    catalogBaseUrl + path,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<ApiResponse<Map<String, Object>>>() {
                    }).getBody();

            if (body == null || body.getData() == null) return null;
            Map<String, Object> data = body.getData();
            return new OwnerInfo(
                    parseLong(data.get("partnerId")),
                    asString(data.get("name")),
                    asString(data.get("slug")),
                    asString(data.get("thumbnailUrl")));
        } catch (Exception e) {
            log.warn("[CatalogOwnerClient] owner lookup failed for {}: {}", path, e.getMessage());
            return null;
        }
    }

    private static String asString(Object v) {
        return v == null ? null : v.toString();
    }

    private static Long parseLong(Object v) {
        if (v == null) return null;
        try {
            return Long.parseLong(v.toString().trim());
        } catch (Exception e) {
            return null;
        }
    }
}

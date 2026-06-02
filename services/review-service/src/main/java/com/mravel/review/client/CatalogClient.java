package com.mravel.review.client;

import com.mravel.common.response.ApiResponse;
import com.mravel.review.model.TargetType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class CatalogClient {

    private final RestTemplate restTemplate;

    @Value("${mravel.services.catalog.base-url}")
    private String catalogBaseUrl;

    /**
     * Gọi catalog-service để cập nhật avgRating, reviewsCount và topAspects.
     */
    public void syncRating(TargetType targetType, String targetId, RatingSyncPayload payload) {
        String typePath = switch (targetType) {
            case HOTEL -> "hotels";
            case RESTAURANT -> "restaurants";
            case PLACE -> "places";
        };

        String url = String.format("%s/api/catalog/%s/%s/rating-sync", catalogBaseUrl, typePath, targetId);

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<RatingSyncPayload> entity = new HttpEntity<>(payload, headers);
            restTemplate.exchange(url, HttpMethod.PUT, entity, Void.class);
        } catch (Exception ex) {
            log.warn("Failed to sync rating to catalog-service for {} {}: {}", targetType, targetId, ex.getMessage());
        }
    }

    /**
     * Resolves owner (partner) + thumbnail for a reviewed property so we can notify
     * the partner. Only HOTEL/RESTAURANT have an owner; returns null otherwise or on error.
     */
    public OwnerInfo getOwner(TargetType targetType, String targetId) {
        String typePath = switch (targetType) {
            case HOTEL -> "hotels";
            case RESTAURANT -> "restaurants";
            default -> null;
        };
        if (typePath == null || targetId == null || targetId.isBlank())
            return null;

        String url = String.format("%s/api/catalog/internal/%s/%s/owner", catalogBaseUrl, typePath, targetId);
        try {
            ApiResponse<Map<String, Object>> body = restTemplate.exchange(
                    url, HttpMethod.GET, null,
                    new ParameterizedTypeReference<ApiResponse<Map<String, Object>>>() {
                    }).getBody();
            if (body == null || body.getData() == null)
                return null;
            Map<String, Object> data = body.getData();
            return new OwnerInfo(
                    parseLong(data.get("partnerId")),
                    asString(data.get("name")),
                    asString(data.get("slug")),
                    asString(data.get("thumbnailUrl")));
        } catch (Exception ex) {
            log.warn("Failed to resolve owner for {} {}: {}", targetType, targetId, ex.getMessage());
            return null;
        }
    }

    private static String asString(Object v) {
        return v == null ? null : v.toString();
    }

    private static Long parseLong(Object v) {
        if (v == null)
            return null;
        try {
            return Long.parseLong(v.toString().trim());
        } catch (Exception e) {
            return null;
        }
    }

    public record OwnerInfo(Long partnerId, String name, String slug, String thumbnailUrl) {
    }

    // ---- Payload DTO ----

    @Getter
    @Setter
    public static class RatingSyncPayload {
        private double avgRating;
        private long reviewsCount;
        /** Top N aspects by mention count — may be null or empty if no aspects exist. */
        private List<AspectKeyword> topAspects;

        public RatingSyncPayload(double avgRating, long reviewsCount, List<AspectKeyword> topAspects) {
            this.avgRating = avgRating;
            this.reviewsCount = reviewsCount;
            this.topAspects = (topAspects == null || topAspects.isEmpty()) ? null : topAspects;
        }
    }

    @Getter
    @Setter
    public static class AspectKeyword {
        private String code;
        private String label;
        private int count;

        public AspectKeyword(String code, String label, int count) {
            this.code = code;
            this.label = label;
            this.count = count;
        }
    }
}

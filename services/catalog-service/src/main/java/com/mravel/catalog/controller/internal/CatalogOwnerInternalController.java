// src/main/java/com/mravel/catalog/controller/internal/CatalogOwnerInternalController.java
package com.mravel.catalog.controller.internal;

import com.mravel.catalog.model.doc.HotelDoc;
import com.mravel.catalog.model.doc.RestaurantDoc;
import com.mravel.catalog.repository.HotelDocRepository;
import com.mravel.catalog.repository.RestaurantDocRepository;
import com.mravel.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Internal, server-to-server lookups used by booking-service and review-service
 * to resolve the owner (partner) and a display thumbnail of a hotel/restaurant.
 *
 * Reachable without a user JWT — all {@code /api/catalog/**} routes are permitAll.
 * Uses a dedicated {@code /internal} base path to avoid colliding with the
 * {@code GET /api/catalog/hotels/{slug}} detail route.
 */
@RestController
@RequestMapping("/api/catalog/internal")
@RequiredArgsConstructor
public class CatalogOwnerInternalController {

    private final HotelDocRepository hotelRepo;
    private final RestaurantDocRepository restaurantRepo;

    /** Minimal owner descriptor consumed by other services for notifications. */
    public record OwnerInfo(String partnerId, String name, String slug, String thumbnailUrl) {
    }

    @GetMapping("/hotels/{id}/owner")
    public ResponseEntity<ApiResponse<OwnerInfo>> hotelOwner(@PathVariable String id) {
        OwnerInfo info = hotelRepo.findById(id)
                .map(h -> new OwnerInfo(
                        h.getPublisher() != null ? h.getPublisher().getPartnerId() : null,
                        pickName(h.getName()),
                        h.getSlug(),
                        hotelCover(h.getImages())))
                .orElse(null);
        return ResponseEntity.ok(ApiResponse.success("OK", info));
    }

    @GetMapping("/restaurants/{id}/owner")
    public ResponseEntity<ApiResponse<OwnerInfo>> restaurantOwner(@PathVariable String id) {
        OwnerInfo info = restaurantRepo.findById(id)
                .map(r -> new OwnerInfo(
                        r.getPublisher() != null ? r.getPublisher().getPartnerId() : null,
                        pickName(r.getName()),
                        r.getSlug(),
                        restaurantCover(r.getImages())))
                .orElse(null);
        return ResponseEntity.ok(ApiResponse.success("OK", info));
    }

    // ── Helpers ───────────────────────────────────────────────────────────────

    private static String pickName(Map<String, String> name) {
        if (name == null || name.isEmpty())
            return null;
        if (name.get("vi") != null && !name.get("vi").isBlank())
            return name.get("vi");
        if (name.get("en") != null && !name.get("en").isBlank())
            return name.get("en");
        return name.values().stream()
                .filter(v -> v != null && !v.isBlank())
                .findFirst().orElse(null);
    }

    private static String hotelCover(List<HotelDoc.Image> images) {
        if (images == null || images.isEmpty())
            return null;
        return images.stream()
                .filter(Objects::nonNull)
                .sorted(Comparator
                        .comparing(HotelDoc.Image::getCover, Comparator.nullsLast(Boolean::compareTo)).reversed()
                        .thenComparing(HotelDoc.Image::getSortOrder, Comparator.nullsLast(Integer::compareTo)))
                .map(HotelDoc.Image::getUrl)
                .filter(Objects::nonNull)
                .findFirst().orElse(null);
    }

    private static String restaurantCover(List<RestaurantDoc.Image> images) {
        if (images == null || images.isEmpty())
            return null;
        return images.stream()
                .filter(Objects::nonNull)
                .sorted(Comparator
                        .comparing(RestaurantDoc.Image::getCover, Comparator.nullsLast(Boolean::compareTo)).reversed()
                        .thenComparing(RestaurantDoc.Image::getSortOrder, Comparator.nullsLast(Integer::compareTo)))
                .map(RestaurantDoc.Image::getUrl)
                .filter(Objects::nonNull)
                .findFirst().orElse(null);
    }
}

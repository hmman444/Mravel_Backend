package com.mravel.catalog.controller.admin;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mravel.catalog.search.es.IndexingService;
import com.mravel.common.response.ApiResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/catalog/admin/search")
@RequiredArgsConstructor
public class AdminSearchController {

    private final IndexingService indexingService;

    /**
     * POST /api/catalog/admin/search/reindex?target=all        → reindex tất cả
     * POST /api/catalog/admin/search/reindex?target=hotels
     * POST /api/catalog/admin/search/reindex?target=restaurants
     * POST /api/catalog/admin/search/reindex?target=places
     */
    @PostMapping("/reindex")
    public ResponseEntity<ApiResponse<Map<String, Integer>>> reindex(
            @RequestParam(defaultValue = "all") String target) {

        Map<String, Integer> result = switch (target.toLowerCase()) {
            case "hotels"      -> Map.of("hotels",      indexingService.reindexHotels());
            case "restaurants" -> Map.of("restaurants", indexingService.reindexRestaurants());
            case "places"      -> Map.of("places",      indexingService.reindexPlaces());
            case "all"         -> indexingService.reindexAll();
            default -> throw new IllegalArgumentException(
                    "Invalid target: " + target + ". Valid values: all, hotels, restaurants, places");
        };

        return ResponseEntity.ok(ApiResponse.success("Reindex completed", result));
    }
}

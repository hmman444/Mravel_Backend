package com.mravel.catalog.controller;

import com.mravel.catalog.model.doc.HotelDoc;
import com.mravel.catalog.model.doc.PlaceDoc;
import com.mravel.catalog.model.doc.RestaurantDoc;
import com.mravel.catalog.repository.HotelDocRepository;
import com.mravel.catalog.repository.PlaceDocRepository;
import com.mravel.catalog.repository.RestaurantDocRepository;
import com.mravel.common.i18n.LocaleConstants;
import com.mravel.catalog.search.es.IndexingService;
import com.mravel.common.response.ApiResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/catalog")
@RequiredArgsConstructor
public class RatingSyncController {

    private final HotelDocRepository hotelRepo;
    private final RestaurantDocRepository restaurantRepo;
    private final PlaceDocRepository placeRepo;
    private final IndexingService indexingService;

    @PutMapping("/hotels/{id}/rating-sync")
    public ResponseEntity<ApiResponse<Void>> syncHotelRating(
            @PathVariable String id, @RequestBody RatingSyncRequest req) {
        hotelRepo.findById(id).ifPresent(doc -> {
            doc.setAvgRating(req.getAvgRating());
            doc.setReviewsCount(req.getReviewsCount().intValue());

            if (req.getTopAspects() != null && !req.getTopAspects().isEmpty()) {
                HotelDoc.ReviewStats stats = doc.getReviewStats() != null
                        ? doc.getReviewStats()
                        : new HotelDoc.ReviewStats();
                stats.setKeywords(req.getTopAspects().stream()
                        .map(a -> HotelDoc.ReviewKeywordStat.builder()
                                .code(a.getCode())
                                .label(a.getLabel() == null ? null : Map.of(LocaleConstants.VI, a.getLabel()))
                                .count(a.getCount())
                                .build())
                        .toList());
                doc.setReviewStats(stats);
            }

            HotelDoc saved = hotelRepo.save(doc);
            indexingService.syncHotel(saved);
        });
        return ResponseEntity.ok(ApiResponse.success("OK", null));
    }

    @PutMapping("/restaurants/{id}/rating-sync")
    public ResponseEntity<ApiResponse<Void>> syncRestaurantRating(
            @PathVariable String id, @RequestBody RatingSyncRequest req) {
        restaurantRepo.findById(id).ifPresent(doc -> {
            doc.setAvgRating(req.getAvgRating());
            doc.setReviewsCount(req.getReviewsCount().intValue());

            if (req.getTopAspects() != null && !req.getTopAspects().isEmpty()) {
                RestaurantDoc.ReviewStats stats = doc.getReviewStats() != null
                        ? doc.getReviewStats()
                        : new RestaurantDoc.ReviewStats();
                stats.setKeywords(req.getTopAspects().stream()
                        .map(a -> RestaurantDoc.ReviewKeywordStat.builder()
                                .code(a.getCode())
                                .label(a.getLabel() == null ? null : Map.of(LocaleConstants.VI, a.getLabel()))
                                .count(a.getCount())
                                .build())
                        .toList());
                doc.setReviewStats(stats);
            }

            RestaurantDoc saved = restaurantRepo.save(doc);
            indexingService.syncRestaurant(saved);
        });
        return ResponseEntity.ok(ApiResponse.success("OK", null));
    }

    @PutMapping("/places/{id}/rating-sync")
    public ResponseEntity<ApiResponse<Void>> syncPlaceRating(
            @PathVariable String id, @RequestBody RatingSyncRequest req) {
        placeRepo.findById(id).ifPresent(doc -> {
            doc.setAvgRating(req.getAvgRating());
            doc.setReviewsCount(req.getReviewsCount().intValue());

            if (req.getTopAspects() != null && !req.getTopAspects().isEmpty()) {
                doc.setReviewKeywords(req.getTopAspects().stream()
                        .map(a -> PlaceDoc.ReviewKeywordStat.builder()
                                .code(a.getCode())
                                .label(a.getLabel() == null ? null : Map.of(LocaleConstants.VI, a.getLabel()))
                                .count(a.getCount())
                                .build())
                        .toList());
            }

            PlaceDoc saved = placeRepo.save(doc);
            indexingService.syncPlace(saved);
        });
        return ResponseEntity.ok(ApiResponse.success("OK", null));
    }

    // ---- Request DTO ----

    @Getter
    @Setter
    public static class RatingSyncRequest {
        private Double avgRating;
        private Long reviewsCount;
        /** null or empty = no aspects to sync (rating-only update) */
        private List<AspectKeyword> topAspects;
    }

    @Getter
    @Setter
    public static class AspectKeyword {
        private String code;
        private String label;
        private Integer count;
    }
}

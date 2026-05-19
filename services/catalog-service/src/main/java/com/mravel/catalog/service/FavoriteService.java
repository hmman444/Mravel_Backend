package com.mravel.catalog.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.mravel.catalog.dto.favorite.FavoriteDtos.FavoriteCountResponse;
import com.mravel.catalog.dto.favorite.FavoriteDtos.FavoriteItemResponse;
import com.mravel.catalog.dto.favorite.FavoriteDtos.FavoriteSummaryResponse;
import com.mravel.catalog.dto.favorite.FavoriteDtos.ToggleFavoriteResponse;
import com.mravel.catalog.model.doc.FavoriteDoc;
import com.mravel.catalog.model.doc.HotelDoc;
import com.mravel.catalog.model.doc.PlaceDoc;
import com.mravel.catalog.model.doc.RestaurantDoc;
import com.mravel.catalog.model.enums.TargetType;
import com.mravel.catalog.repository.FavoriteDocRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class FavoriteService {

    private final FavoriteDocRepository favoriteRepo;
    private final MongoTemplate mongoTemplate;

    /**
     * Toggle favorite: nếu đã favorite thì xóa, chưa thì thêm.
     * Đồng thời cập nhật favoriteCount trên object tương ứng.
     */
    public ToggleFavoriteResponse toggle(Long userId, TargetType targetType, String targetId) {
        Optional<FavoriteDoc> existing = favoriteRepo.findByUserIdAndTargetTypeAndTargetId(userId, targetType, targetId);

        boolean favorited;
        if (existing.isPresent()) {
            // Bỏ favorite
            favoriteRepo.delete(existing.get());
            incrementFavoriteCount(targetType, targetId, -1);
            favorited = false;
        } else {
            // Thêm favorite
            FavoriteDoc doc = FavoriteDoc.builder()
                    .userId(userId)
                    .targetType(targetType)
                    .targetId(targetId)
                    .createdAt(Instant.now())
                    .build();
            favoriteRepo.save(doc);
            incrementFavoriteCount(targetType, targetId, 1);
            favorited = true;
        }

        long count = getFavoriteCount(targetType, targetId);
        return ToggleFavoriteResponse.builder()
                .favorited(favorited)
                .favoriteCount(count)
                .build();
    }

    /**
     * Lấy số lượng favorite của 1 object.
     */
    public FavoriteCountResponse getCount(TargetType targetType, String targetId) {
        long count = getFavoriteCount(targetType, targetId);
        return FavoriteCountResponse.builder().count(count).build();
    }

    /**
     * Lấy summary (count + trạng thái favorited) cho 1 object.
     * Nếu userId == null (chưa login) thì favorited = false.
     */
    public FavoriteSummaryResponse getSummary(Long userId, TargetType targetType, String targetId) {
        long count = getFavoriteCount(targetType, targetId);
        boolean favorited = false;
        if (userId != null) {
            favorited = favoriteRepo.existsByUserIdAndTargetTypeAndTargetId(userId, targetType, targetId);
        }
        return FavoriteSummaryResponse.builder()
                .count(count)
                .favorited(favorited)
                .build();
    }

    /**
     * Danh sách favorites của user, có phân trang.
     * Nếu có targetType thì lọc theo loại.
     */
    public Page<FavoriteItemResponse> getMyFavorites(Long userId, TargetType targetType, Pageable pageable) {
        Page<FavoriteDoc> page;
        if (targetType != null) {
            page = favoriteRepo.findByUserIdAndTargetTypeOrderByCreatedAtDesc(userId, targetType, pageable);
        } else {
            page = favoriteRepo.findByUserIdOrderByCreatedAtDesc(userId, pageable);
        }

        List<FavoriteItemResponse> items = page.getContent().stream()
                .map(this::toItemResponse)
                .toList();

        return new PageImpl<>(items, pageable, page.getTotalElements());
    }

    // ===================== PRIVATE HELPERS =====================

    /**
     * Lấy favoriteCount từ chính document của object (denormalized).
     * Fall back về count từ favorites collection nếu field chưa tồn tại.
     */
    private long getFavoriteCount(TargetType targetType, String targetId) {
        String collectionName = getCollectionName(targetType);
        Query query = new Query(Criteria.where("_id").is(targetId));
        query.fields().include("favoriteCount");

        var doc = mongoTemplate.findOne(query, java.util.Map.class, collectionName);
        if (doc != null && doc.get("favoriteCount") instanceof Number num) {
            return Math.max(0, num.longValue());
        }
        // Fallback: đếm từ favorites collection
        return favoriteRepo.countByTargetTypeAndTargetId(targetType, targetId);
    }

    /**
     * Tăng/giảm favoriteCount trên document gốc bằng $inc (atomic).
     * Đảm bảo không bao giờ < 0.
     */
    private void incrementFavoriteCount(TargetType targetType, String targetId, int delta) {
        String collectionName = getCollectionName(targetType);
        Query query = new Query(Criteria.where("_id").is(targetId));
        Update update = new Update().inc("favoriteCount", delta);
        mongoTemplate.updateFirst(query, update, collectionName);

        // Đảm bảo không âm
        if (delta < 0) {
            Query negativeCheck = new Query(
                    Criteria.where("_id").is(targetId)
                            .and("favoriteCount").lt(0)
            );
            Update fix = new Update().set("favoriteCount", 0);
            mongoTemplate.updateFirst(negativeCheck, fix, collectionName);
        }
    }

    private String getCollectionName(TargetType type) {
        return switch (type) {
            case HOTEL -> "hotels";
            case RESTAURANT -> "restaurants";
            case PLACE -> "places";
        };
    }

    /**
     * Map FavoriteDoc -> FavoriteItemResponse, kèm thông tin cơ bản của object.
     */
    private FavoriteItemResponse toItemResponse(FavoriteDoc fav) {
        FavoriteItemResponse.FavoriteItemResponseBuilder builder = FavoriteItemResponse.builder()
                .id(fav.getId())
                .targetType(fav.getTargetType())
                .targetId(fav.getTargetId())
                .createdAt(fav.getCreatedAt());

        try {
            switch (fav.getTargetType()) {
                case HOTEL -> {
                    HotelDoc hotel = mongoTemplate.findById(fav.getTargetId(), HotelDoc.class);
                    if (hotel != null) {
                        builder.targetName(hotel.getName());
                        builder.targetSlug(hotel.getSlug());
                        if (hotel.getImages() != null && !hotel.getImages().isEmpty()) {
                            builder.targetImage(hotel.getImages().get(0).getUrl());
                        }
                    }
                }
                case RESTAURANT -> {
                    RestaurantDoc restaurant = mongoTemplate.findById(fav.getTargetId(), RestaurantDoc.class);
                    if (restaurant != null) {
                        builder.targetName(restaurant.getName());
                        builder.targetSlug(restaurant.getSlug());
                        if (restaurant.getImages() != null && !restaurant.getImages().isEmpty()) {
                            builder.targetImage(restaurant.getImages().get(0).getUrl());
                        }
                    }
                }
                case PLACE -> {
                    PlaceDoc place = mongoTemplate.findById(fav.getTargetId(), PlaceDoc.class);
                    if (place != null) {
                        builder.targetName(place.getName());
                        builder.targetSlug(place.getSlug());
                        if (place.getImages() != null && !place.getImages().isEmpty()) {
                            builder.targetImage(place.getImages().get(0).getUrl());
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.warn("Không thể lấy thông tin object cho favorite {}: {}", fav.getId(), e.getMessage());
        }

        return builder.build();
    }
}

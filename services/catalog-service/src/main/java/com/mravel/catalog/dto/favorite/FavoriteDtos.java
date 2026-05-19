package com.mravel.catalog.dto.favorite;

import com.mravel.catalog.model.enums.TargetType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTOs cho tính năng Favorite.
 */
public class FavoriteDtos {

    // ===================== REQUEST =====================

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ToggleFavoriteRequest {
        @NotNull(message = "targetType không được để trống")
        private TargetType targetType;

        @NotBlank(message = "targetId không được để trống")
        private String targetId;
    }

    // ===================== RESPONSE =====================

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ToggleFavoriteResponse {
        private boolean favorited;
        private long favoriteCount;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class FavoriteCountResponse {
        private long count;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class FavoriteSummaryResponse {
        private long count;
        private boolean favorited;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class FavoriteItemResponse {
        private String id;
        private TargetType targetType;
        private String targetId;
        private String targetName;
        private String targetSlug;
        private String targetImage;
        private java.time.Instant createdAt;
    }
}

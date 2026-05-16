package com.mravel.catalog.controller;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mravel.catalog.client.AuthServiceClient;
import com.mravel.catalog.dto.favorite.FavoriteDtos.FavoriteCountResponse;
import com.mravel.catalog.dto.favorite.FavoriteDtos.FavoriteItemResponse;
import com.mravel.catalog.dto.favorite.FavoriteDtos.FavoriteSummaryResponse;
import com.mravel.catalog.dto.favorite.FavoriteDtos.ToggleFavoriteRequest;
import com.mravel.catalog.dto.favorite.FavoriteDtos.ToggleFavoriteResponse;
import com.mravel.catalog.model.enums.TargetType;
import com.mravel.catalog.service.FavoriteService;
import com.mravel.common.response.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/catalog/favorites")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;
    private final AuthServiceClient authServiceClient;

    /**
     * Toggle favorite (thêm/bỏ yêu thích).
     * Yêu cầu đăng nhập (Authorization header).
     */
    @PostMapping("/toggle")
    public ResponseEntity<ApiResponse<ToggleFavoriteResponse>> toggle(
            @RequestHeader(value = "Authorization") String bearer,
            @RequestBody @Valid ToggleFavoriteRequest request
    ) {
        Long userId = authServiceClient.extractUserId(bearer);
        if (userId == null) {
            return ResponseEntity.status(401)
                    .body(ApiResponse.error("Vui lòng đăng nhập để sử dụng tính năng yêu thích"));
        }

        ToggleFavoriteResponse result = favoriteService.toggle(userId, request.getTargetType(), request.getTargetId());
        String msg = result.isFavorited() ? "Đã thêm vào yêu thích" : "Đã bỏ yêu thích";
        return ResponseEntity.ok(ApiResponse.success(msg, result));
    }

    /**
     * Lấy số lượng favorite của 1 object.
     * Public – không cần đăng nhập.
     */
    @GetMapping("/count")
    public ResponseEntity<ApiResponse<FavoriteCountResponse>> getCount(
            @RequestParam TargetType targetType,
            @RequestParam String targetId
    ) {
        FavoriteCountResponse result = favoriteService.getCount(targetType, targetId);
        return ResponseEntity.ok(ApiResponse.success("OK", result));
    }

    /**
     * Lấy summary (count + trạng thái favorited) cho 1 object.
     * Optional JWT – nếu có token thì trả trạng thái đã tim, không có thì favorited = false.
     */
    @GetMapping("/summary")
    public ResponseEntity<ApiResponse<FavoriteSummaryResponse>> getSummary(
            @RequestHeader(value = "Authorization", required = false) String bearer,
            @RequestParam TargetType targetType,
            @RequestParam String targetId
    ) {
        Long userId = (bearer != null) ? authServiceClient.extractUserId(bearer) : null;
        FavoriteSummaryResponse result = favoriteService.getSummary(userId, targetType, targetId);
        return ResponseEntity.ok(ApiResponse.success("OK", result));
    }

    /**
     * Lấy danh sách các đối tượng user đã yêu thích.
     * Yêu cầu đăng nhập.
     */
    @GetMapping("/me")
    public ResponseEntity<ApiResponse<Page<FavoriteItemResponse>>> getMyFavorites(
            @RequestHeader(value = "Authorization") String bearer,
            @RequestParam(required = false) TargetType targetType,
            @ParameterObject Pageable pageable
    ) {
        Long userId = authServiceClient.extractUserId(bearer);
        if (userId == null) {
            return ResponseEntity.status(401)
                    .body(ApiResponse.error("Vui lòng đăng nhập"));
        }

        Page<FavoriteItemResponse> result = favoriteService.getMyFavorites(userId, targetType, pageable);
        return ResponseEntity.ok(ApiResponse.success("OK", result));
    }
}

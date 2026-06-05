package com.mravel.admin.controller;

import com.mravel.admin.client.ReviewAdminClient;
import com.mravel.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Admin: xem & kiểm duyệt đánh giá tiêu cực của khách sạn/quán ăn. Proxy sang review-service.
 * Bảo mật: SecurityConfig đã gắn /api/admin/** -> hasRole('ADMIN').
 */
@RestController
@RequestMapping("/api/admin/reviews")
@RequiredArgsConstructor
public class AdminReviewController {

    private final ReviewAdminClient reviewAdminClient;

    @GetMapping("/negative")
    public ResponseEntity<ApiResponse<?>> negative(
            @RequestHeader("Authorization") String authorization,
            @RequestParam String targetType,
            @RequestParam(required = false) String targetId,
            @RequestParam(defaultValue = "2") int maxRating,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return reviewAdminClient.listNegative(targetType, targetId, maxRating, page, size,
                extractBearer(authorization));
    }

    @GetMapping("/negative/count")
    public ResponseEntity<ApiResponse<?>> negativeCount(
            @RequestHeader("Authorization") String authorization,
            @RequestParam String targetType,
            @RequestParam(defaultValue = "2") int maxRating) {
        return reviewAdminClient.negativeCount(targetType, maxRating, extractBearer(authorization));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> delete(
            @RequestHeader("Authorization") String authorization,
            @PathVariable Long id) {
        return reviewAdminClient.delete(id, extractBearer(authorization));
    }

    private String extractBearer(String authorizationHeader) {
        if (authorizationHeader == null)
            return "";
        String s = authorizationHeader.trim();
        if (s.toLowerCase().startsWith("bearer "))
            return s.substring(7).trim();
        return s;
    }
}

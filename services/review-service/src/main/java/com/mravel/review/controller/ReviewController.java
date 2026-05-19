package com.mravel.review.controller;

import com.mravel.common.response.ApiResponse;
import com.mravel.review.dto.*;
import com.mravel.review.model.TargetType;
import com.mravel.review.security.CurrentUserService;
import com.mravel.review.service.ReviewAspectService;
import com.mravel.review.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewAspectService reviewAspectService;
    private final CurrentUserService currentUserService;

    @PostMapping
    public ResponseEntity<ApiResponse<ReviewResponse>> createReview(
            @Valid @RequestBody CreateReviewRequest request) {
        Long userId = currentUserService.getId();
        ReviewResponse review = reviewService.createReview(userId, request);
        return ResponseEntity.ok(ApiResponse.success("Đánh giá thành công", review));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<ReviewResponse>>> getReviews(
            @RequestParam TargetType targetType,
            @RequestParam String targetId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ReviewResponse> reviews = reviewService.getReviews(targetType, targetId, pageable);
        return ResponseEntity.ok(ApiResponse.success("Lấy danh sách đánh giá thành công", reviews));
    }

    @GetMapping("/summary")
    public ResponseEntity<ApiResponse<ReviewSummaryResponse>> getSummary(
            @RequestParam TargetType targetType,
            @RequestParam String targetId) {
        ReviewSummaryResponse summary = reviewService.getSummary(targetType, targetId);
        return ResponseEntity.ok(ApiResponse.success("Lấy thống kê đánh giá thành công", summary));
    }

    @GetMapping("/my")
    public ResponseEntity<ApiResponse<ReviewResponse>> getMyReview(
            @RequestParam TargetType targetType,
            @RequestParam String targetId) {
        Long userId = currentUserService.getId();
        ReviewResponse review = reviewService.getMyReview(userId, targetType, targetId);
        return ResponseEntity.ok(ApiResponse.success("OK", review));
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<ApiResponse<ReviewResponse>> updateReview(
            @PathVariable Long reviewId,
            @Valid @RequestBody UpdateReviewRequest request) {
        Long userId = currentUserService.getId();
        ReviewResponse review = reviewService.updateReview(userId, reviewId, request);
        return ResponseEntity.ok(ApiResponse.success("Cập nhật đánh giá thành công", review));
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<ApiResponse<Void>> deleteReview(@PathVariable Long reviewId) {
        Long userId = currentUserService.getId();
        String role = currentUserService.getRole();
        reviewService.deleteReview(userId, role, reviewId);
        return ResponseEntity.ok(ApiResponse.success("Xóa đánh giá thành công", null));
    }

    @GetMapping("/aspects")
    public ResponseEntity<ApiResponse<List<ReviewAspectDefinitionDTO>>> getAspects(
            @RequestParam TargetType category) {
        List<ReviewAspectDefinitionDTO> aspects = reviewAspectService.getDefinitions(category);
        return ResponseEntity.ok(ApiResponse.success("OK", aspects));
    }
}

package com.mravel.review.controller;

import com.mravel.common.response.ApiResponse;
import com.mravel.review.dto.ReviewResponse;
import com.mravel.review.model.TargetType;
import com.mravel.review.security.CurrentUserService;
import com.mravel.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Admin: xem & kiểm duyệt đánh giá tiêu cực của khách sạn/quán ăn.
 * Bảo mật: SecurityConfig của review-service chỉ dùng authenticated() nên
 * vai trò ADMIN được kiểm tra trong code (giống deleteReview) — admin-service
 * forward Bearer token của admin xuống đây.
 */
@RestController
@RequestMapping("/api/reviews/admin")
@RequiredArgsConstructor
public class AdminReviewController {

    private final ReviewService reviewService;
    private final CurrentUserService currentUserService;

    private void requireAdmin() {
        if (!"ADMIN".equalsIgnoreCase(currentUserService.getRole())) {
            throw new SecurityException("Chỉ quản trị viên mới được phép thực hiện thao tác này");
        }
    }

    /** Danh sách đánh giá tiêu cực (rating ≤ maxRating). targetId tuỳ chọn để lọc 1 cơ sở. */
    @GetMapping("/negative")
    public ResponseEntity<ApiResponse<Page<ReviewResponse>>> negative(
            @RequestParam TargetType targetType,
            @RequestParam(required = false) String targetId,
            @RequestParam(defaultValue = "2") int maxRating,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        requireAdmin();
        Pageable pageable = PageRequest.of(page, size);
        Page<ReviewResponse> reviews = reviewService.getNegativeReviews(targetType, targetId, maxRating, pageable);
        return ResponseEntity.ok(ApiResponse.success("Lấy danh sách đánh giá tiêu cực thành công", reviews));
    }

    /** Tổng số đánh giá tiêu cực của một loại cơ sở (cho thẻ thống kê). */
    @GetMapping("/negative/count")
    public ResponseEntity<ApiResponse<Long>> negativeCount(
            @RequestParam TargetType targetType,
            @RequestParam(defaultValue = "2") int maxRating) {
        requireAdmin();
        return ResponseEntity.ok(ApiResponse.success("OK", reviewService.countNegative(targetType, maxRating)));
    }

    /** Admin xoá một đánh giá (vi phạm/spam). Tái dùng logic xoá có sẵn (đã cho phép ADMIN). */
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long reviewId) {
        requireAdmin();
        reviewService.deleteReview(currentUserService.getId(), currentUserService.getRole(), reviewId);
        return ResponseEntity.ok(ApiResponse.success("Đã xóa đánh giá", null));
    }
}

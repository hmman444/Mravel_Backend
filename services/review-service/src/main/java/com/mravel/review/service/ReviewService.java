package com.mravel.review.service;

import com.mravel.common.notification.NotificationTypes;
import com.mravel.common.response.UserProfileResponse;
import com.mravel.review.client.AdminLookupClient;
import com.mravel.review.client.CatalogClient;
import com.mravel.review.client.NotificationClient;
import com.mravel.review.client.PlanClient;
import com.mravel.review.client.UserProfileClient;
import com.mravel.review.dto.*;
import com.mravel.review.model.Review;
import com.mravel.review.model.ReviewAspect;
import com.mravel.review.model.TargetType;
import com.mravel.review.repository.ReviewAspectDefinitionRepository;
import com.mravel.review.repository.ReviewAspectRepository;
import com.mravel.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewAspectRepository reviewAspectRepository;
    private final ReviewAspectDefinitionRepository aspectDefinitionRepository;
    private final UserProfileClient userProfileClient;
    private final CatalogClient catalogClient;
    private final PlanClient planClient;
    private final NotificationClient notificationClient;
    private final AdminLookupClient adminLookupClient;

    @Transactional
    public ReviewResponse createReview(Long userId, CreateReviewRequest request) {
        reviewRepository.findByUserIdAndTargetTypeAndTargetId(userId, request.getTargetType(), request.getTargetId())
                .ifPresent(existing -> {
                    throw new IllegalStateException("Bạn đã đánh giá rồi. Hãy chỉnh sửa đánh giá hiện tại.");
                });

        // Chặn đánh giá ảo: chỉ cho đánh giá dịch vụ đã trải nghiệm trong một lịch trình đã qua.
        if (!planClient.isExperienced(request.getTargetType().name(), request.getTargetId(),
                request.getTargetSlug(), request.getTargetName())) {
            throw new IllegalStateException("Bạn chưa trải nghiệm dịch vụ này nên chưa thể đánh giá.");
        }

        Review review = Review.builder()
                .userId(userId)
                .targetId(request.getTargetId())
                .targetType(request.getTargetType())
                .rating(request.getRating())
                .content(request.getContent())
                .build();

        review = reviewRepository.save(review);

        saveAspects(review, request.getAspects());

        syncRatingToCatalog(request.getTargetType(), request.getTargetId());

        notifyNewReview(userId, review, request);

        return toResponse(review);
    }

    /**
     * Notifies the property owner (partner) of a new review, and — for low ratings
     * (≤ 2★) — the admins for moderation. Fail-silent: never breaks review creation.
     */
    private void notifyNewReview(Long reviewerId, Review review, CreateReviewRequest request) {
        try {
            TargetType type = review.getTargetType();
            CatalogClient.OwnerInfo owner = catalogClient.getOwner(type, review.getTargetId());

            String propertyName = (request.getTargetName() != null && !request.getTargetName().isBlank())
                    ? request.getTargetName()
                    : (owner != null ? owner.name() : null);
            if (propertyName == null)
                propertyName = "dịch vụ của bạn";

            String slug = (request.getTargetSlug() != null && !request.getTargetSlug().isBlank())
                    ? request.getTargetSlug()
                    : (owner != null ? owner.slug() : null);
            String publicLink = publicPropertyLink(type, slug);
            String thumb = owner != null ? owner.thumbnailUrl() : null;
            int rating = review.getRating() != null ? review.getRating() : 0;

            // 1) Notify the partner who owns the property.
            if (owner != null && owner.partnerId() != null && !owner.partnerId().equals(reviewerId)) {
                Map<String, Object> data = new HashMap<>();
                if (publicLink != null) data.put("deepLink", publicLink);
                if (thumb != null) data.put("thumbnailUrl", thumb);
                data.put("targetId", review.getTargetId());
                data.put("rating", rating);
                safeNotify(owner.partnerId(), reviewerId,
                        NotificationTypes.REVIEW_NEW_FOR_PARTNER,
                        "Đánh giá mới",
                        "Có đánh giá " + rating + "★ mới cho " + propertyName + ".",
                        data);
            }

            // 2) Notify admins about low-rated reviews (moderation signal).
            if (rating > 0 && rating <= 2) {
                String adminLink = adminPropertyLink(type, review.getTargetId());
                for (Long adminId : adminLookupClient.getAdminIds()) {
                    if (adminId == null || adminId.equals(reviewerId)) continue;
                    Map<String, Object> data = new HashMap<>();
                    if (adminLink != null) data.put("deepLink", adminLink);
                    if (thumb != null) data.put("thumbnailUrl", thumb);
                    data.put("targetId", review.getTargetId());
                    data.put("rating", rating);
                    safeNotify(adminId, reviewerId,
                            NotificationTypes.ADMIN_NEW_REVIEW,
                            "Đánh giá thấp cần kiểm duyệt",
                            "Có đánh giá " + rating + "★ cho " + propertyName + " cần được xem xét.",
                            data);
                }
            }
        } catch (Exception ex) {
            log.warn("notifyNewReview failed for review {}: {}", review.getId(), ex.getMessage());
        }
    }

    private void safeNotify(Long recipientId, Long actorId, String type,
            String title, String message, Map<String, Object> data) {
        try {
            notificationClient.createNotification(recipientId, actorId, type, title, message, data);
        } catch (Exception e) {
            log.warn("notify failed type={} recipient={}: {}", type, recipientId, e.getMessage());
        }
    }

    private static String publicPropertyLink(TargetType type, String slug) {
        if (slug == null || slug.isBlank()) return null;
        return switch (type) {
            case HOTEL -> "/hotels/" + slug;
            case RESTAURANT -> "/restaurants/" + slug;
            case PLACE -> "/place/" + slug;
        };
    }

    private static String adminPropertyLink(TargetType type, String targetId) {
        if (targetId == null) return "/admin/services";
        return switch (type) {
            case HOTEL -> "/admin/services/hotels/" + targetId;
            case RESTAURANT -> "/admin/services/restaurants/" + targetId;
            case PLACE -> "/admin/places";
        };
    }

    public Page<ReviewResponse> getReviews(TargetType targetType, String targetId, Pageable pageable) {
        Page<Review> page = reviewRepository.findByTargetTypeAndTargetIdOrderByCreatedAtDesc(
                targetType, targetId, pageable);
        return page.map(this::toResponse);
    }

    public ReviewSummaryResponse getSummary(TargetType targetType, String targetId) {
        Double avg = reviewRepository.avgRatingByTarget(targetType, targetId);
        long total = reviewRepository.countByTargetTypeAndTargetId(targetType, targetId);
        List<Object[]> dist = reviewRepository.ratingDistribution(targetType, targetId);

        Map<Integer, Long> distribution = new HashMap<>();
        for (int i = 1; i <= 5; i++) distribution.put(i, 0L);
        for (Object[] row : dist) {
            Integer star = (Integer) row[0];
            Long count = (Long) row[1];
            distribution.put(star, count);
        }

        List<PopularAspectDTO> popularAspects = reviewAspectRepository
                .countAspectMentions(targetType, targetId)
                .stream()
                .limit(5)
                .map(row -> PopularAspectDTO.builder()
                        .code((String) row[0])
                        .labelVi((String) row[1])
                        .labelEn((String) row[2])
                        .mentionCount((Long) row[3])
                        .build())
                .toList();

        return ReviewSummaryResponse.builder()
                .averageRating(Math.round(avg * 10.0) / 10.0)
                .totalReviews(total)
                .ratingDistribution(distribution)
                .popularAspects(popularAspects)
                .build();
    }

    @Transactional
    public ReviewResponse updateReview(Long userId, Long reviewId, UpdateReviewRequest request) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy đánh giá"));

        if (!review.getUserId().equals(userId)) {
            throw new SecurityException("Bạn không có quyền chỉnh sửa đánh giá này");
        }

        review.setRating(request.getRating());
        review.setContent(request.getContent());

        reviewAspectRepository.deleteAllByReviewId(reviewId);
        review = reviewRepository.save(review);

        saveAspects(review, request.getAspects());

        syncRatingToCatalog(review.getTargetType(), review.getTargetId());

        return toResponse(review);
    }

    @Transactional
    public void deleteReview(Long userId, String role, Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy đánh giá"));

        boolean isOwner = review.getUserId().equals(userId);
        boolean isAdmin = "ADMIN".equalsIgnoreCase(role);

        if (!isOwner && !isAdmin) {
            throw new SecurityException("Bạn không có quyền xóa đánh giá này");
        }

        TargetType targetType = review.getTargetType();
        String targetId = review.getTargetId();

        reviewRepository.delete(review);

        syncRatingToCatalog(targetType, targetId);
    }

    public ReviewResponse getMyReview(Long userId, TargetType targetType, String targetId) {
        return reviewRepository.findByUserIdAndTargetTypeAndTargetId(userId, targetType, targetId)
                .map(this::toResponse)
                .orElse(null);
    }

    /** Có được phép viết đánh giá không: đã có đánh giá (cho sửa) hoặc đã trải nghiệm dịch vụ. */
    public boolean canReview(Long userId, TargetType targetType, String targetId, String slug, String name) {
        boolean already = reviewRepository
                .findByUserIdAndTargetTypeAndTargetId(userId, targetType, targetId).isPresent();
        if (already) return true;
        return planClient.isExperienced(targetType.name(), targetId, slug, name);
    }

    private void saveAspects(Review review, List<AspectCommentInput> inputs) {
        if (inputs == null || inputs.isEmpty()) return;

        for (AspectCommentInput input : inputs) {
            aspectDefinitionRepository
                    .findByCategoryAndActiveTrueOrderBySortOrderAsc(review.getTargetType())
                    .stream()
                    .filter(d -> d.getCode().equalsIgnoreCase(input.getAspectCode()))
                    .findFirst()
                    .ifPresent(def -> {
                        ReviewAspect aspect = ReviewAspect.builder()
                                .review(review)
                                .definition(def)
                                .comment(input.getComment())
                                .build();
                        reviewAspectRepository.save(aspect);
                    });
        }
    }

    private void syncRatingToCatalog(TargetType targetType, String targetId) {
        try {
            Double avg = reviewRepository.avgRatingByTarget(targetType, targetId);
            long count = reviewRepository.countByTargetTypeAndTargetId(targetType, targetId);

            List<CatalogClient.AspectKeyword> topAspects = reviewAspectRepository
                    .countAspectMentions(targetType, targetId)
                    .stream()
                    .limit(5)
                    .map(row -> new CatalogClient.AspectKeyword(
                            (String) row[0],
                            (String) row[1],   // labelVi
                            ((Long) row[3]).intValue()))
                    .toList();

            catalogClient.syncRating(targetType, targetId,
                    new CatalogClient.RatingSyncPayload(avg, count, topAspects));
        } catch (Exception ex) {
            log.warn("Sync rating to catalog failed: {}", ex.getMessage());
        }
    }

    private ReviewResponse toResponse(Review review) {
        List<AspectCommentResponse> aspects = loadAspects(review.getId());

        ReviewResponse resp = ReviewResponse.builder()
                .id(review.getId())
                .userId(review.getUserId())
                .targetId(review.getTargetId())
                .targetType(review.getTargetType())
                .rating(review.getRating())
                .content(review.getContent())
                .aspects(aspects.isEmpty() ? null : aspects)
                .createdAt(review.getCreatedAt())
                .updatedAt(review.getUpdatedAt())
                .build();

        try {
            UserProfileResponse user = userProfileClient.getUserById(review.getUserId());
            if (user != null) {
                resp.setUserFullname(user.getFullname());
                resp.setUserAvatar(user.getAvatar());
            }
        } catch (Exception ex) {
            log.warn("Failed to fetch user profile for userId {}: {}", review.getUserId(), ex.getMessage());
        }

        return resp;
    }

    private List<AspectCommentResponse> loadAspects(Long reviewId) {
        if (reviewId == null) return Collections.emptyList();
        return reviewAspectRepository.findByReviewId(reviewId)
                .stream()
                .map(a -> AspectCommentResponse.builder()
                        .code(a.getDefinition().getCode())
                        .labelVi(a.getDefinition().getLabelVi())
                        .labelEn(a.getDefinition().getLabelEn())
                        .comment(a.getComment())
                        .build())
                .toList();
    }
}

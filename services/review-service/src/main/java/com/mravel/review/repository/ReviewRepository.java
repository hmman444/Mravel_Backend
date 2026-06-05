package com.mravel.review.repository;

import com.mravel.review.model.Review;
import com.mravel.review.model.TargetType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Page<Review> findByTargetTypeAndTargetIdOrderByCreatedAtDesc(
            TargetType targetType, String targetId, Pageable pageable);

    Optional<Review> findByUserIdAndTargetTypeAndTargetId(
            Long userId, TargetType targetType, String targetId);

    long countByTargetTypeAndTargetId(TargetType targetType, String targetId);

    @Query("SELECT COALESCE(AVG(r.rating), 0) FROM Review r WHERE r.targetType = :type AND r.targetId = :id")
    Double avgRatingByTarget(@Param("type") TargetType type, @Param("id") String id);

    @Query("SELECT r.rating, COUNT(r) FROM Review r WHERE r.targetType = :type AND r.targetId = :id GROUP BY r.rating")
    java.util.List<Object[]> ratingDistribution(@Param("type") TargetType type, @Param("id") String id);

    /**
     * Liệt kê đánh giá tiêu cực (rating &lt;= maxRating) cho admin kiểm duyệt.
     * targetId tuỳ chọn: null = tất cả khách sạn/quán ăn cùng loại, có = đúng 1 cơ sở.
     */
    @Query("SELECT r FROM Review r WHERE r.targetType = :type " +
            "AND (:targetId IS NULL OR r.targetId = :targetId) " +
            "AND r.rating <= :maxRating ORDER BY r.createdAt DESC")
    Page<Review> findNegativeReviews(@Param("type") TargetType type,
            @Param("targetId") String targetId,
            @Param("maxRating") int maxRating,
            Pageable pageable);

    @Query("SELECT COUNT(r) FROM Review r WHERE r.targetType = :type AND r.rating <= :maxRating")
    long countNegativeByType(@Param("type") TargetType type, @Param("maxRating") int maxRating);
}

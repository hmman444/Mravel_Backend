package com.mravel.review.repository;

import com.mravel.review.model.ReviewAspect;
import com.mravel.review.model.TargetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewAspectRepository extends JpaRepository<ReviewAspect, Long> {

    @Modifying
    @Query("DELETE FROM ReviewAspect a WHERE a.review.id = :reviewId")
    void deleteAllByReviewId(@Param("reviewId") Long reviewId);

    @Query("SELECT a FROM ReviewAspect a JOIN FETCH a.definition WHERE a.review.id = :reviewId")
    List<ReviewAspect> findByReviewId(@Param("reviewId") Long reviewId);

    @Query("""
            SELECT a.definition.code, a.definition.labelVi, a.definition.labelEn, COUNT(a)
            FROM ReviewAspect a
            WHERE a.review.targetType = :type AND a.review.targetId = :id
            GROUP BY a.definition.code, a.definition.labelVi, a.definition.labelEn
            ORDER BY COUNT(a) DESC
            """)
    List<Object[]> countAspectMentions(@Param("type") TargetType type, @Param("id") String id);
}

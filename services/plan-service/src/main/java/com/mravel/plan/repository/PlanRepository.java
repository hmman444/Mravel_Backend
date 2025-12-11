package com.mravel.plan.repository;

import com.mravel.plan.model.Plan;
import com.mravel.plan.model.Visibility;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlanRepository extends JpaRepository<Plan, Long> {
    Page<Plan> findByVisibility(Visibility visibility, Pageable pageable);

    List<Plan> findByAuthorId(Long authorId);

    @Query("""
                select p from Plan p
                where p.visibility = 'PUBLIC'
                   or p.authorId = :viewerId
                   or p.id in :memberPlanIds
                order by p.createdAt desc
            """)
    Page<Plan> findVisibleForUser(@Param("viewerId") Long viewerId,
            @Param("memberPlanIds") List<Long> memberPlanIds,
            Pageable pageable);

    @Query("""
            SELECT p FROM Plan p
            WHERE p.authorId = :ownerId
              AND (
                    p.visibility = 'PUBLIC'
                 OR (p.visibility = 'FRIENDS' AND :isFriend = TRUE)
                 OR (p.visibility = 'PRIVATE' AND p.id IN :memberPlanIds)
              )
            ORDER BY p.createdAt DESC
            """)
    Page<Plan> findAllPlansOfUserWithVisibility(
            @Param("ownerId") Long ownerId,
            @Param("isFriend") boolean isFriend,
            @Param("memberPlanIds") List<Long> memberPlanIds,
            Pageable pageable);

    Page<Plan> findByAuthorId(Long authorId, Pageable pageable);

}

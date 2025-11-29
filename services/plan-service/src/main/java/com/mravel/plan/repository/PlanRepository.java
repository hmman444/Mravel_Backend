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

    Page<Plan> findByAuthor_Id(Long authorId, Pageable pageable);

    List<Plan> findByAuthor_Id(Long authorId);

    List<Plan> findByIdIn(List<Long> ids);

    @Query("""
                select p from Plan p
                where p.visibility = 'PUBLIC'
                   or p.author.id = :viewerId
                   or p.id in :memberPlanIds
                order by p.createdAt desc
            """)
    Page<Plan> findVisibleForUser(@Param("viewerId") Long viewerId,
            @Param("memberPlanIds") List<Long> memberPlanIds,
            Pageable pageable);

}

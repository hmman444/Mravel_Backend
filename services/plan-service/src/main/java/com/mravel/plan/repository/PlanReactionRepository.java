package com.mravel.plan.repository;

import com.mravel.plan.model.PlanReaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface PlanReactionRepository extends JpaRepository<PlanReaction, Long> {
    List<PlanReaction> findByPlanId(Long planId);

    Optional<PlanReaction> findByPlanIdAndUserId(Long planId, Long userId);

    void deleteByPlanIdAndUserId(Long planId, Long userId);

    /** Đếm reaction theo nhiều plan trong 1 query (tránh N+1). [planId, count] */
    @Query("select r.plan.id, count(r) from PlanReaction r where r.plan.id in :ids group by r.plan.id")
    List<Object[]> countByPlanIds(@Param("ids") List<Long> ids);
}

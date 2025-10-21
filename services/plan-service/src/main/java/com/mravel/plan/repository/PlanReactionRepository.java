package com.mravel.plan.repository;

import com.mravel.plan.model.PlanReaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface PlanReactionRepository extends JpaRepository<PlanReaction, Long> {
    List<PlanReaction> findByPlanId(Long planId);
    Optional<PlanReaction> findByPlanIdAndUserId(Long planId, String userId);
}

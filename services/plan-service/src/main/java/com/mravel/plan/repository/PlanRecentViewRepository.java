package com.mravel.plan.repository;

import com.mravel.plan.dto.board.PlanRecentView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlanRecentViewRepository extends JpaRepository<PlanRecentView, Long> {

    Optional<PlanRecentView> findByUserIdAndPlanId(Long userId, Long planId);

    List<PlanRecentView> findTop20ByUserIdOrderByViewedAtDesc(Long userId);

    void deleteByUserIdAndPlanId(Long userId, Long planId);
}
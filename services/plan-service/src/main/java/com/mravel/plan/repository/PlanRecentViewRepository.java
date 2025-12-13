package com.mravel.plan.repository;

import com.mravel.plan.dto.board.PlanRecentView;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;
import java.util.Optional;

public interface PlanRecentViewRepository extends JpaRepository<PlanRecentView, Long> {

    @Modifying
    @Transactional
    void deleteByPlanId(Long planId);

    @Modifying
    @Transactional
    void deleteByUserIdAndPlanId(Long userId, Long planId);

    Optional<PlanRecentView> findByUserIdAndPlanId(Long userId, Long planId);

    List<PlanRecentView> findTop20ByUserIdOrderByViewedAtDesc(Long userId);

}
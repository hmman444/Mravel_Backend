package com.mravel.plan.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mravel.plan.dto.board.PlanRequest;
import com.mravel.plan.model.PlanRequestStatus;

public interface PlanRequestRepository extends JpaRepository<PlanRequest, Long> {

    boolean existsByPlanIdAndUserIdAndStatus(Long planId, Long userId, PlanRequestStatus status);

    List<PlanRequest> findByPlanIdAndStatus(Long planId, PlanRequestStatus status);

    Optional<PlanRequest> findByPlanIdAndUserIdAndStatus(Long planId, Long userId, PlanRequestStatus status);
}

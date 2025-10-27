package com.mravel.plan.repository;

import com.mravel.plan.model.PlanList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanListRepository extends JpaRepository<PlanList, Long> {
    List<PlanList> findByPlanIdOrderByPositionAsc(Long planId);

    long countByPlanId(Long planId);
}

package com.mravel.plan.repository;

import com.mravel.plan.model.PlanLabel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlanLabelRepository extends JpaRepository<PlanLabel, Long> {
    List<PlanLabel> findByPlanId(Long planId);

    Optional<PlanLabel> findByPlanIdAndText(Long planId, String text);
}

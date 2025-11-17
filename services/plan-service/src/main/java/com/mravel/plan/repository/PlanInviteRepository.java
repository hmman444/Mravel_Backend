package com.mravel.plan.repository;

import com.mravel.plan.model.PlanInvite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanInviteRepository extends JpaRepository<PlanInvite, Long> {
    List<PlanInvite> findByPlanId(Long planId);

    void deleteByEmailAndPlanId(String email, Long planId);
}

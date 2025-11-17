package com.mravel.plan.repository;

import com.mravel.plan.model.PlanInviteToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlanInviteTokenRepository extends JpaRepository<PlanInviteToken, Long> {

    Optional<PlanInviteToken> findByToken(String token);

    boolean existsByEmailAndPlanId(String email, Long planId);
}

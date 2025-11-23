package com.mravel.plan.repository;

import com.mravel.plan.model.PlanMember;
import com.mravel.plan.model.PlanRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.List;

public interface PlanMemberRepository extends JpaRepository<PlanMember, Long> {

    Optional<PlanMember> findByPlanIdAndUserId(Long planId, Long userId);

    List<PlanMember> findByPlanId(Long planId);

    List<PlanMember> findByUserId(Long userId);

    boolean existsByPlanIdAndUserIdAndRole(Long planId, Long userId, PlanRole role);

    boolean existsByPlanIdAndUserId(Long planId, Long userId);

    @Query("SELECT pm.plan.id FROM PlanMember pm WHERE pm.userId = :userId")
    List<Long> findPlanIdsByUserId(Long userId);
}

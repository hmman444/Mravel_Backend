package com.mravel.plan.repository;

import com.mravel.plan.model.PlanInviteToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PlanInviteTokenRepository extends JpaRepository<PlanInviteToken, Long> {

    @Modifying
    @Query("delete from PlanInviteToken t where t.plan.id = :planId")
    void deleteByPlanId(@Param("planId") Long planId);

    Optional<PlanInviteToken> findByToken(String token);

    boolean existsByEmailAndPlanId(String email, Long planId);

    List<PlanInviteToken> findByPlanId(Long planId);

    void deleteByEmailAndPlanId(String email, Long planId);

}

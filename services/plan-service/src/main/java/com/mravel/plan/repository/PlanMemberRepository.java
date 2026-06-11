package com.mravel.plan.repository;

import com.mravel.plan.model.PlanMember;
import com.mravel.plan.model.PlanRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;
import java.util.List;

public interface PlanMemberRepository extends JpaRepository<PlanMember, Long> {

    Optional<PlanMember> findByPlanIdAndUserId(Long planId, Long userId);

    List<PlanMember> findByPlanId(Long planId);

    List<PlanMember> findByUserId(Long userId);

    boolean existsByPlanIdAndUserIdAndRole(Long planId, Long userId, PlanRole role);

    boolean existsByPlanIdAndUserId(Long planId, Long userId);

    @Query("SELECT pm.plan.id FROM PlanMember pm WHERE pm.userId = :userId")
    List<Long> findPlanIdsByUserId(@Param("userId") Long userId);

    @Query("SELECT pm.userId FROM PlanMember pm WHERE pm.plan.id = :planId")
    Set<Long> findUserIdsByPlanId(@Param("planId") Long planId);

    /** Đếm thành viên theo nhiều plan trong 1 query (tránh N+1). [planId, count] */
    @Query("SELECT pm.plan.id, count(pm) FROM PlanMember pm WHERE pm.plan.id IN :ids GROUP BY pm.plan.id")
    List<Object[]> countByPlanIds(@Param("ids") List<Long> ids);
}

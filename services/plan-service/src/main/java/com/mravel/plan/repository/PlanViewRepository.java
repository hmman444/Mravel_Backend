package com.mravel.plan.repository;

import com.mravel.plan.model.PlanView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlanViewRepository extends JpaRepository<PlanView, Long> {

    boolean existsByUserIdAndPlanId(Long userId, Long planId);

    /** Bản ghi throttle của 1 user cho 1 plan (lưu thời điểm xem được tính gần nhất). */
    Optional<PlanView> findByUserIdAndPlanId(Long userId, Long planId);

    /** Số người xem duy nhất của 1 plan. */
    long countByPlanId(Long planId);
}

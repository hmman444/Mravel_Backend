package com.mravel.plan.repository;

import com.mravel.plan.model.Plan;
import com.mravel.plan.model.Visibility;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Long> {
    Page<Plan> findByVisibilityInOrderByCreatedAtDesc(Iterable<Visibility> visibilities, Pageable pageable);
}

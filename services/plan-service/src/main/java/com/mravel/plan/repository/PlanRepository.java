package com.mravel.plan.repository;

import com.mravel.plan.model.Plan;
import com.mravel.plan.model.Visibility;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Long> {
    Page<Plan> findByVisibility(Visibility visibility, Pageable pageable);

    Page<Plan> findByAuthor_Id(Long authorId, Pageable pageable);

    List<Plan> findByIdIn(List<Long> ids);
}

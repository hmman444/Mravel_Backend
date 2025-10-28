package com.mravel.plan.repository;

import com.mravel.plan.model.PlanComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanCommentRepository extends JpaRepository<PlanComment, Long> {
}

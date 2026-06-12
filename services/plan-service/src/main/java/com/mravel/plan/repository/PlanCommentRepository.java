package com.mravel.plan.repository;

import com.mravel.plan.model.PlanComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlanCommentRepository extends JpaRepository<PlanComment, Long> {

    /** Đếm comment theo nhiều plan trong 1 query (tránh N+1). [planId, count] */
    @Query("select c.plan.id, count(c) from PlanComment c where c.plan.id in :ids group by c.plan.id")
    List<Object[]> countByPlanIds(@Param("ids") List<Long> ids);
}

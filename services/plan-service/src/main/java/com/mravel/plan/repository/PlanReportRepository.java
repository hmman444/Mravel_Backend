package com.mravel.plan.repository;

import com.mravel.plan.model.PlanReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlanReportRepository extends JpaRepository<PlanReport, Long> {

    Page<PlanReport> findByStatusOrderByCreatedAtDesc(PlanReport.Status status, Pageable pageable);

    Page<PlanReport> findAllByOrderByCreatedAtDesc(Pageable pageable);

    long countByStatus(PlanReport.Status status);

    /** Tất cả báo cáo của một plan (mới nhất trước) — dùng cho màn chi tiết. */
    List<PlanReport> findByPlanIdOrderByCreatedAtDesc(Long planId);

    /** Đếm báo cáo PENDING theo nhiều plan trong 1 query (tránh N+1). [planId, count] */
    @Query("select r.planId, count(r) from PlanReport r where r.status = :status and r.planId in :ids group by r.planId")
    List<Object[]> countByStatusAndPlanIds(@Param("status") PlanReport.Status status, @Param("ids") List<Long> ids);
}

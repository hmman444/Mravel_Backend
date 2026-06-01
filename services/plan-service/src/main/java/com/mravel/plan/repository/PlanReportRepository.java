package com.mravel.plan.repository;

import com.mravel.plan.model.PlanReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanReportRepository extends JpaRepository<PlanReport, Long> {

    Page<PlanReport> findByStatusOrderByCreatedAtDesc(PlanReport.Status status, Pageable pageable);

    Page<PlanReport> findAllByOrderByCreatedAtDesc(Pageable pageable);

    long countByStatus(PlanReport.Status status);
}

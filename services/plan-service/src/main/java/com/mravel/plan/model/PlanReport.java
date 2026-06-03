package com.mravel.plan.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

/**
 * Báo cáo (report) một plan/post vi phạm. Hàng đợi cho admin kiểm duyệt.
 */
@Entity
@Table(name = "plan_reports", indexes = {
        @Index(name = "idx_plan_report_status", columnList = "status, created_at"),
        @Index(name = "idx_plan_report_plan", columnList = "plan_id")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanReport {

    public enum Reason {
        SPAM, INAPPROPRIATE, COPYRIGHT, SCAM, OTHER
    }

    public enum Status {
        PENDING, REVIEWING, RESOLVED, DISMISSED
    }

    public enum Action {
        NONE, TAKEDOWN
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "plan_id", nullable = false)
    private Long planId;

    @Column(name = "reporter_id", nullable = false)
    private Long reporterId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Reason reason;

    @Column(length = 1000)
    private String detail;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @Builder.Default
    private Status status = Status.PENDING;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "resolved_by")
    private Long resolvedBy;

    @Column(name = "resolved_at")
    private Instant resolvedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "action_taken", length = 20)
    private Action actionTaken;
}

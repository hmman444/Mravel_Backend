package com.mravel.plan.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

/**
 * Phase 3b — idempotency store for command API calls.
 * TTL 24h; cleaned by PlanCommandPurgeJob.
 * Unique on operation_id — duplicate POSTs with same key return stored response.
 */
@Entity
@Table(
        name = "plan_commands",
        indexes = @Index(name = "idx_cmd_created_at", columnList = "created_at")
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanCommand {

    @Id
    @Column(name = "operation_id", length = 36)
    private String operationId;

    @Column(name = "plan_id", nullable = false)
    private Long planId;

    /**
     * Serialized CommandResponse JSON.
     * Returned verbatim on duplicate requests.
     */
    @Lob
    @Column(name = "response_snapshot", columnDefinition = "TEXT")
    private String responseSnapshot;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Builder.Default
    private Instant createdAt = Instant.now();
}

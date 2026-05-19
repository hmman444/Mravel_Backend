package com.mravel.plan.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "plan_revision_log", uniqueConstraints = @UniqueConstraint(name = "uq_plan_revision", columnNames = {
                "plan_id",
                "revision" }), indexes = @Index(name = "idx_plan_created", columnList = "plan_id, created_at"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanRevisionLog {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "plan_id", nullable = false)
        private Long planId;

        @Column(nullable = false)
        private Long revision;

        /**
         * Full serialized PlanBoardEventV2 JSON.
         * Stored as TEXT so gap recovery can return it without re-deserializing.
         */
        @Lob
        @Column(name = "event_envelope", columnDefinition = "TEXT", nullable = false)
        private String eventEnvelope;

        @Column(name = "created_at", nullable = false, updatable = false)
        @Builder.Default
        private Instant createdAt = Instant.now();
}

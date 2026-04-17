package com.mravel.plan.kafka;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlanBoardEventV2 {

    /** Always "2" — allows consumers to switch parsers on field value. */
    private String envelopeVersion;

    /** Kafka partition key — all events for a plan land on the same partition. */
    private Long planId;

    /** Entity type: PLAN, LIST, CARD */
    private String entityType;

    /** PK of the affected entity. Null for plan-level / bulk operations. */
    private Long entityId;

    /** CREATE, UPDATE, DELETE, REORDER, MOVE */
    private String operationType;

    /**
     * Changed fields only (JSON Merge Patch, RFC 7396).
     * Shape varies by entityType × operationType — see PlanBoardCommandService.
     */
    private Map<String, Object> patch;

    /** User who triggered the operation. */
    private Long actorId;

    /**
     * Client-generated UUID echoed back verbatim.
     * Used for client-side deduplication of self-events.
     */
    private String operationId;

    /**
     * Monotonically increasing counter per plan.
     * Guaranteed by row-level increment in the same transaction as the mutation.
     */
    private Long revision;

    /** Wall-clock ISO-8601 — for display only, NOT used for ordering. */
    private String occurredAt;

    /** Epoch-millis timestamp — used by consumers to measure WS delivery lag. */
    private Long timestamp;
}

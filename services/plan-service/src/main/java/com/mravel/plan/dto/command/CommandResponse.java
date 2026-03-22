package com.mravel.plan.dto.command;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

/**
 * Phase 3 — unified response shape for all granular command endpoints.
 *
 * On success the controller returns HTTP 200 with this body.
 * On idempotent replay the same stored body is returned.
 * Clients use {@code revision} to advance their local lastRevision counter.
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommandResponse {

    /** Entity type that was mutated: PLAN, LIST, CARD, COST_EXTRA, MEMBER */
    private String entityType;

    /** PK of the mutated entity. */
    private Long entityId;

    /** The operation that was performed: CREATE, UPDATE, DELETE, REORDER, MOVE, DUPLICATE */
    private String operationType;

    /**
     * Only the fields that were changed (JSON Merge Patch, RFC 7396).
     * For CREATE: the full new entity.
     * For DELETE: null.
     * Clients apply this to their local Redux state.
     */
    private Map<String, Object> patch;

    /** Echo of the client-supplied Idempotency-Key. */
    private String operationId;

    /** New boardRevision after this mutation. */
    private Long revision;

    /** Wall-clock timestamp (ISO-8601) for display only. */
    private String occurredAt;
}

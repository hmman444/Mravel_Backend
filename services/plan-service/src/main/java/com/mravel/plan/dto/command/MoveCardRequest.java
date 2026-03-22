package com.mravel.plan.dto.command;

import lombok.Data;

/** Phase 3 — request body for PATCH /cards/{cardId}/move */
@Data
public class MoveCardRequest {
    /** Destination list id */
    private Long targetListId;
    /** Position within the destination list */
    private Integer targetPosition;
    /** Optimistic version of the card being moved */
    private Long version;
}

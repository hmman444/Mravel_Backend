package com.mravel.plan.dto.command;

import lombok.Data;

@Data
public class MoveCardRequest {
    /** Destination list id */
    private Long targetListId;
    /** Position within the destination list */
    private Integer targetPosition;
    /** Optimistic version of the card being moved */
    private Long version;
}

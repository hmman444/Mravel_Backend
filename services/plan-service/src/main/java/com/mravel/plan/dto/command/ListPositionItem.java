package com.mravel.plan.dto.command;

import lombok.Data;

/** Phase 3 — one item in a reorder-lists or reorder-cards positions array. */
@Data
public class ListPositionItem {
    private Long listId;
    private Integer position;
}

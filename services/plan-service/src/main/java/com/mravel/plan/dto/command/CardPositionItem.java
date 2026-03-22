package com.mravel.plan.dto.command;

import lombok.Data;

/** Phase 3 — one item in a reorder-cards-within-list positions array. */
@Data
public class CardPositionItem {
    private Long cardId;
    private Integer position;
}

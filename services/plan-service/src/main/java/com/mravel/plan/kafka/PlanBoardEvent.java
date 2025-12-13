package com.mravel.plan.kafka;

import com.mravel.plan.dto.board.BoardResponse;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlanBoardEvent {

    private String eventType; // BOARD_UPDATED, CREATE_CARD, UPDATE_CARD, DELETE_CARD, REORDER,...
    private Long planId;
    private Long actorId; // user thực hiện thao tác
    private Long timestamp;

    // BoardResponse mới nhất sau khi thay đổi
    private BoardResponse board;
}

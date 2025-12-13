package com.mravel.realtime.dto;

import lombok.Data;

@Data
public class PlanBoardEvent {

    private String eventType;
    private Long planId;
    private Long actorId;
    private Long timestamp;

    private Object board;
}

package com.mravel.realtime.dto;

import lombok.Data;

import java.util.Map;

@Data
public class PlanBoardEventV2 {

    private String envelopeVersion;
    private Long planId;
    private String entityType;
    private Long entityId;
    private String operationType;

    /** Changed fields only — shape varies by entityType × operationType. */
    private Map<String, Object> patch;

    private Long actorId;
    private String operationId;
    private Long revision;
    private String occurredAt;

    /** Epoch-millis — used for WS delivery lag metrics. */
    private Long timestamp;
}

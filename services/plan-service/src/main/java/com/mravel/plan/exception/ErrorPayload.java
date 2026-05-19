package com.mravel.plan.exception;

import lombok.Builder;

import java.time.Instant;
import java.util.Map;

@Builder
public record ErrorPayload(
        Instant timestamp,
        int status,
        String error,
        String code,
        String message,
        String path,
        String traceId,
        Map<String, Object> details) {
}

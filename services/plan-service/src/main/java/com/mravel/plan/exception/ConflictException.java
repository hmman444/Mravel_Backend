package com.mravel.plan.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * Phase 3a — thrown when optimistic lock version mismatch is detected.
 * Maps to HTTP 409 Conflict.
 */
@Getter
public class ConflictException extends ApiException {

    private final Long currentVersion;
    private final Long yourVersion;

    public ConflictException(Long currentVersion, Long yourVersion) {
        super(HttpStatus.CONFLICT, ErrorCodes.STALE_VERSION, "Entity was modified by another user.",
                buildDetails(currentVersion, yourVersion));
        this.currentVersion = currentVersion;
        this.yourVersion = yourVersion;
    }

    private static Map<String, Object> buildDetails(Long currentVersion, Long yourVersion) {
        Map<String, Object> details = new HashMap<>();
        details.put("currentVersion", currentVersion != null ? currentVersion : -1);
        details.put("yourVersion", yourVersion != null ? yourVersion : -1);
        details.put("hint", "Re-fetch entity and retry.");
        return details;
    }
}

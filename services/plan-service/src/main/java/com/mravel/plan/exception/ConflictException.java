package com.mravel.plan.exception;

import lombok.Getter;

/**
 * Phase 3a — thrown when optimistic lock version mismatch is detected.
 * Maps to HTTP 409 Conflict.
 */
@Getter
public class ConflictException extends RuntimeException {

    private final String code;
    private final Long currentVersion;
    private final Long yourVersion;

    public ConflictException(Long currentVersion, Long yourVersion) {
        super("Entity was modified by another user.");
        this.code = "STALE_VERSION";
        this.currentVersion = currentVersion;
        this.yourVersion = yourVersion;
    }
}

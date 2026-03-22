package com.mravel.plan.exception;

import org.springframework.http.HttpStatus;

/**
 * Phase 3 — thrown when a user does not have the required role.
 * Maps to HTTP 403 Forbidden.
 */
public class ForbiddenException extends ApiException {
    public ForbiddenException(String message) {
        super(HttpStatus.FORBIDDEN, ErrorCodes.FORBIDDEN, message);
    }

    public ForbiddenException(String code, String message) {
        super(HttpStatus.FORBIDDEN, code, message);
    }
}

package com.mravel.plan.exception;

/**
 * Phase 3 — thrown when a user does not have the required role.
 * Maps to HTTP 403 Forbidden.
 */
public class ForbiddenException extends RuntimeException {
    public ForbiddenException(String message) {
        super(message);
    }
}

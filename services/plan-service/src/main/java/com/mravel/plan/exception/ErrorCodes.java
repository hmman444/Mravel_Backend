package com.mravel.plan.exception;

/**
 * Shared error code catalog for plan-service.
 * Frontend keeps a mirrored list to drive UI behavior by code instead of
 * message text.
 */
public final class ErrorCodes {

    private ErrorCodes() {
    }

    public static final String BAD_REQUEST = "BAD_REQUEST";
    public static final String NOT_FOUND = "NOT_FOUND";
    public static final String FORBIDDEN = "FORBIDDEN";
    public static final String CONFLICT = "CONFLICT";

    public static final String STALE_VERSION = "STALE_VERSION";
    public static final String VALIDATION_FAILED = "VALIDATION_FAILED";
    public static final String CONSTRAINT_VIOLATION = "CONSTRAINT_VIOLATION";
    public static final String DATA_INTEGRITY_VIOLATION = "DATA_INTEGRITY_VIOLATION";
    public static final String INVALID_PERSISTENCE_STATE = "INVALID_PERSISTENCE_STATE";
    public static final String INTERNAL_ERROR = "INTERNAL_ERROR";

    public static final String ACCESS_REQUEST_ALREADY_SUBMITTED = "ACCESS_REQUEST_ALREADY_SUBMITTED";
    public static final String ACCESS_ALREADY_GRANTED = "ACCESS_ALREADY_GRANTED";
    public static final String ACCESS_VIEW_ALREADY_GRANTED = "ACCESS_VIEW_ALREADY_GRANTED";
}

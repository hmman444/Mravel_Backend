package com.mravel.plan.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

/**
 * Phase 3 — global exception handler for plan-service.
 * Converts domain exceptions to structured HTTP responses.
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<Map<String, Object>> handleConflict(ConflictException ex) {
        log.warn("optimistic_conflict code={} currentVersion={} yourVersion={}",
                ex.getCode(), ex.getCurrentVersion(), ex.getYourVersion());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
                "error", "CONFLICT",
                "code", ex.getCode(),
                "message", ex.getMessage(),
                "currentVersion", ex.getCurrentVersion() != null ? ex.getCurrentVersion() : -1,
                "yourVersion", ex.getYourVersion() != null ? ex.getYourVersion() : -1,
                "hint", "Re-fetch entity and retry."
        ));
    }

    /**
     * Hibernate's optimistic lock exception — wraps as ConflictException response.
     */
    @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
    public ResponseEntity<Map<String, Object>> handleOptimisticLock(
            ObjectOptimisticLockingFailureException ex) {
        log.warn("hibernate_optimistic_lock: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
                "error", "CONFLICT",
                "code", "STALE_VERSION",
                "message", "Entity was modified by another user.",
                "hint", "Re-fetch entity and retry."
        ));
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<Map<String, Object>> handleForbidden(ForbiddenException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of(
                "error", "FORBIDDEN",
                "message", ex.getMessage()
        ));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntime(RuntimeException ex) {
        log.error("Unhandled RuntimeException: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                "error", "INTERNAL_ERROR",
                "message", ex.getMessage()
        ));
    }
}

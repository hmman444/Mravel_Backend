package com.mravel.plan.exception;

import com.mravel.common.response.ApiResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Global exception handler for plan-service.
 * Provides consistent error envelopes with code, path, traceId, and optional
 * details.
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(ApiException.class)
        public ResponseEntity<ApiResponse<ErrorPayload>> handleApiException(ApiException ex,
                        HttpServletRequest request) {
                HttpStatus status = ex.getStatus();
                logWithLevel(status, "api_exception code={} message={}", ex.getCode(), ex.getMessage());
                return buildResponse(status, ex.getCode(), ex.getMessage(), request, ex.getDetails());
        }

        @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
        public ResponseEntity<ApiResponse<ErrorPayload>> handleOptimisticLock(
                        ObjectOptimisticLockingFailureException ex,
                        HttpServletRequest request) {
                log.warn("hibernate_optimistic_lock: {}", ex.getMessage());
                return buildResponse(
                                HttpStatus.CONFLICT,
                                ErrorCodes.STALE_VERSION,
                                "Dữ liệu đã được cập nhật bởi người khác. Vui lòng tải lại và thử lại.",
                                request,
                                Map.of("hint", "Re-fetch entity and retry."));
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ApiResponse<ErrorPayload>> handleValidation(
                        MethodArgumentNotValidException ex,
                        HttpServletRequest request) {
                Map<String, Object> details = new LinkedHashMap<>();
                details.put("fields", ex.getBindingResult().getFieldErrors().stream()
                                .collect(Collectors.toMap(
                                                FieldError::getField,
                                                fe -> fe.getDefaultMessage() == null ? "invalid"
                                                                : fe.getDefaultMessage(),
                                                (a, b) -> a,
                                                LinkedHashMap::new)));

                return buildResponse(
                                HttpStatus.BAD_REQUEST,
                                ErrorCodes.VALIDATION_FAILED,
                                "Dữ liệu không hợp lệ.",
                                request,
                                details);
        }

        @ExceptionHandler(ConstraintViolationException.class)
        public ResponseEntity<ApiResponse<ErrorPayload>> handleConstraintViolation(
                        ConstraintViolationException ex,
                        HttpServletRequest request) {
                Map<String, Object> details = new LinkedHashMap<>();
                details.put("violations", ex.getConstraintViolations().stream()
                                .map(v -> Map.of(
                                                "property",
                                                v.getPropertyPath() == null ? "" : v.getPropertyPath().toString(),
                                                "message", v.getMessage()))
                                .toList());

                return buildResponse(
                                HttpStatus.BAD_REQUEST,
                                ErrorCodes.CONSTRAINT_VIOLATION,
                                "Dữ liệu không hợp lệ.",
                                request,
                                details);
        }

        @ExceptionHandler({
                        IllegalArgumentException.class,
                        HttpMessageNotReadableException.class,
                        MethodArgumentTypeMismatchException.class
        })
        public ResponseEntity<ApiResponse<ErrorPayload>> handleBadRequest(Exception ex, HttpServletRequest request) {
                return buildResponse(
                                HttpStatus.BAD_REQUEST,
                                ErrorCodes.BAD_REQUEST,
                                safeMessage(ex.getMessage(), "Yêu cầu không hợp lệ."),
                                request,
                                Map.of());
        }

        @ExceptionHandler({ EntityNotFoundException.class })
        public ResponseEntity<ApiResponse<ErrorPayload>> handleNotFound(Exception ex, HttpServletRequest request) {
                return buildResponse(
                                HttpStatus.NOT_FOUND,
                                ErrorCodes.NOT_FOUND,
                                safeMessage(ex.getMessage(), "Không tìm thấy dữ liệu."),
                                request,
                                Map.of());
        }

        @ExceptionHandler({ DataIntegrityViolationException.class, JpaSystemException.class })
        public ResponseEntity<ApiResponse<ErrorPayload>> handleDataIntegrity(Exception ex, HttpServletRequest request) {
                log.warn("data_integrity_violation: {}", ex.getMessage());
                return buildResponse(
                                HttpStatus.CONFLICT,
                                ErrorCodes.DATA_INTEGRITY_VIOLATION,
                                "Dữ liệu vi phạm ràng buộc hệ thống.",
                                request,
                                Map.of());
        }

        @ExceptionHandler(InvalidDataAccessApiUsageException.class)
        public ResponseEntity<ApiResponse<ErrorPayload>> handleInvalidDataAccess(
                        InvalidDataAccessApiUsageException ex,
                        HttpServletRequest request) {
                log.error("invalid_data_access_api_usage: {}", ex.getMessage(), ex);
                return buildResponse(
                                HttpStatus.INTERNAL_SERVER_ERROR,
                                ErrorCodes.INVALID_PERSISTENCE_STATE,
                                "Lỗi xử lý dữ liệu nội bộ.",
                                request,
                                Map.of());
        }

        @ExceptionHandler(RuntimeException.class)
        public ResponseEntity<ApiResponse<ErrorPayload>> handleRuntime(RuntimeException ex,
                        HttpServletRequest request) {
                RuntimeMapping mapped = mapLegacyRuntime(ex);
                logWithLevel(mapped.status(), "runtime_exception code={} message={}", mapped.code(), ex.getMessage());
                return buildResponse(mapped.status(), mapped.code(), mapped.message(), request, Map.of());
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<ApiResponse<ErrorPayload>> handleUnknown(Exception ex, HttpServletRequest request) {
                log.error("Unhandled exception: {}", ex.getMessage(), ex);
                return buildResponse(
                                HttpStatus.INTERNAL_SERVER_ERROR,
                                ErrorCodes.INTERNAL_ERROR,
                                "Đã xảy ra lỗi nội bộ. Vui lòng thử lại sau.",
                                request,
                                Map.of());
        }

        private ResponseEntity<ApiResponse<ErrorPayload>> buildResponse(
                        HttpStatus status,
                        String code,
                        String message,
                        HttpServletRequest request,
                        Map<String, Object> details) {

                ErrorPayload payload = ErrorPayload.builder()
                                .timestamp(Instant.now())
                                .status(status.value())
                                .error(status.getReasonPhrase())
                                .code(code)
                                .message(message)
                                .path(request.getRequestURI())
                                .traceId(MDC.get("traceId"))
                                .details(details == null ? Map.of() : details)
                                .build();

                ApiResponse<ErrorPayload> body = ApiResponse.<ErrorPayload>builder()
                                .success(false)
                                .message(message)
                                .data(payload)
                                .timestamp(Instant.now())
                                .build();

                return ResponseEntity.status(status).body(body);
        }

        private String safeMessage(String value, String fallback) {
                if (value == null || value.isBlank())
                        return fallback;
                return value;
        }

        private RuntimeMapping mapLegacyRuntime(RuntimeException ex) {
                String raw = ex.getMessage() == null ? "" : ex.getMessage();
                String msg = raw.toLowerCase();

                if (msg.contains("not found")) {
                        return new RuntimeMapping(HttpStatus.NOT_FOUND, ErrorCodes.NOT_FOUND, raw);
                }

                if (msg.contains("permission") || msg.contains("not invited") || msg.contains("forbidden")) {
                        return new RuntimeMapping(HttpStatus.FORBIDDEN, ErrorCodes.FORBIDDEN, raw);
                }

                if (msg.contains("invalid") || msg.contains("required") || msg.contains("must be")) {
                        return new RuntimeMapping(HttpStatus.BAD_REQUEST, ErrorCodes.BAD_REQUEST, raw);
                }

                if (msg.contains("already") || msg.contains("duplicate") || msg.contains("cannot")) {
                        return new RuntimeMapping(HttpStatus.CONFLICT, ErrorCodes.CONFLICT, raw);
                }

                return new RuntimeMapping(
                                HttpStatus.INTERNAL_SERVER_ERROR,
                                ErrorCodes.INTERNAL_ERROR,
                                "Đã xảy ra lỗi nội bộ. Vui lòng thử lại sau.");
        }

        private void logWithLevel(HttpStatus status, String pattern, Object... args) {
                if (status.is5xxServerError()) {
                        log.error(pattern, args);
                        return;
                }
                if (status == HttpStatus.BAD_REQUEST || status == HttpStatus.NOT_FOUND) {
                        log.info(pattern, args);
                        return;
                }
                log.warn(pattern, args);
        }

        private record RuntimeMapping(HttpStatus status, String code, String message) {
        }
}

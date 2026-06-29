package com.mravel.admin.exception;

import com.mravel.common.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResourceAccessException;

// Advice riêng vì application chỉ scan com.mravel.admin, không gồm com.mravel.common.
@Slf4j
@RestControllerAdvice
public class AdminGlobalExceptionHandler {

    // Xung đột trạng thái nghiệp vụ (vd: hotel/restaurant không ở trạng thái cho phép duyệt/chặn...).
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ApiResponse<?>> handleIllegalState(IllegalStateException ex) {
        log.warn("Admin state conflict: {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ApiResponse.error(ex.getMessage()));
    }

    // Dữ liệu đầu vào không hợp lệ: thiếu trường, tham số sai, lý do rỗng...
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<?>> handleIllegalArgument(IllegalArgumentException ex) {
        log.warn("Admin bad request: {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(ex.getMessage()));
    }

    // Body JSON sai định dạng / enum sai / thiếu field bắt buộc khi parse.
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<?>> handleUnreadable(HttpMessageNotReadableException ex) {
        log.warn("Admin invalid payload: {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error("Dữ liệu gửi lên không hợp lệ"));
    }

    // Không gọi được service phụ thuộc (catalog/auth/booking/plan/review/notification) qua RestTemplate.
    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<ApiResponse<?>> handleDownstreamUnavailable(ResourceAccessException ex) {
        log.error("Admin downstream unavailable: {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(ApiResponse.error("Không kết nối được tới dịch vụ liên quan. Vui lòng thử lại sau."));
    }

    @ExceptionHandler(SecurityException.class)
    public ResponseEntity<ApiResponse<?>> handleSecurity(SecurityException ex) {
        log.warn("Admin security violation: {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ApiResponse.error(ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleOther(Exception ex) {
        log.error("Unexpected admin error", ex);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("Đã xảy ra lỗi hệ thống"));
    }
}

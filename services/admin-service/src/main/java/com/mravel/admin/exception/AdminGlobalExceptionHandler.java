package com.mravel.admin.exception;

import com.mravel.common.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResourceAccessException;

/**
 * Bộ xử lý ngoại lệ cho admin-service.
 *
 * Lý do tồn tại: @SpringBootApplication ở com.mravel.admin KHÔNG component-scan
 * com.mravel.common nên GlobalExceptionHandler dùng chung không hoạt động, và
 * service cũng chưa có advice riêng. Hệ quả: lỗi nghiệp vụ/validation
 * (IllegalArgumentException) và các RuntimeException khác rơi xuống cách xử lý
 * mặc định của Spring và trả về 500 chung chung "Đã xảy ra lỗi hệ thống",
 * giấu mất lý do thật. Advice này mirror booking-service để trả thông điệp cụ thể.
 */
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
    // Trước đây rơi xuống handleOther -> 500 "Đã xảy ra lỗi hệ thống" (giấu mất lý do thật).
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

    // Không gọi được service phụ thuộc (catalog/auth/booking/plan/review/notification) qua RestTemplate:
    // connection refused / timeout / DNS... -> trước đây thành 500 chung chung.
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

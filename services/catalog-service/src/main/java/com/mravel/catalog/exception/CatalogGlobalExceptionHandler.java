// src/main/java/com/mravel/catalog/exception/CatalogGlobalExceptionHandler.java
package com.mravel.catalog.exception;

import com.mravel.common.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CatalogGlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<?>> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(ex.getMessage()));
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ApiResponse<?>> handleIllegalState(IllegalStateException ex) {
        // Dùng cho case hết phòng, trạng thái không hợp lệ...
        return ResponseEntity
                .status(HttpStatus.CONFLICT)  // 409
                .body(ApiResponse.error(ex.getMessage()));
    }
}
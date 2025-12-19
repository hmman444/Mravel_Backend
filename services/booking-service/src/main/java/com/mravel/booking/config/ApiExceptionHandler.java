package com.mravel.booking.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mravel.common.response.ApiResponse;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(SecurityException.class)
    public ResponseEntity<ApiResponse<?>> handleSecurity(SecurityException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ApiResponse.error(e.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<?>> handleBadRequest(IllegalArgumentException e) {
        return ResponseEntity.badRequest()
                .body(ApiResponse.error(e.getMessage()));
    }
}
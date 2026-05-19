package com.mravel.catalog.exception;

import com.mravel.common.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CatalogGlobalExceptionHandler {

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ApiResponse<?>> handleIllegalState(IllegalStateException ex) {
        log.warn("Catalog state conflict: {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ApiResponse.error(ex.getMessage()));
    }
}
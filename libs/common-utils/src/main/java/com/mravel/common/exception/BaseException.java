package com.mravel.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;

public class BaseException extends RuntimeException {

    @NonNull
    private final HttpStatus status;

    public BaseException(String message, @NonNull HttpStatus status) {
        super(message);
        this.status = status;
    }

    @NonNull
    public HttpStatus getStatus() {
        return status;
    }
}

package com.mravel.plan.exception;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends ApiException {
    public ForbiddenException(String message) {
        super(HttpStatus.FORBIDDEN, ErrorCodes.FORBIDDEN, message);
    }

    public ForbiddenException(String code, String message) {
        super(HttpStatus.FORBIDDEN, code, message);
    }
}

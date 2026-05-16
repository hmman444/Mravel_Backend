package com.mravel.partner.exception;

import com.mravel.common.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;

public class PartnerException extends BaseException {

    public PartnerException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }

    public PartnerException(String message, @NonNull HttpStatus status) {
        super(message, status);
    }
}

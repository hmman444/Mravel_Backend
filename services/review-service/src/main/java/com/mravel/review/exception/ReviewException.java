package com.mravel.review.exception;

import com.mravel.common.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;

public class ReviewException extends BaseException {

    public ReviewException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }

    public ReviewException(String message, @NonNull HttpStatus status) {
        super(message, status);
    }
}

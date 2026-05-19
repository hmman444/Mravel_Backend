package com.mravel.booking.exception;

import com.mravel.common.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;

public class BookingException extends BaseException {

    public BookingException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }

    public BookingException(String message, @NonNull HttpStatus status) {
        super(message, status);
    }
}

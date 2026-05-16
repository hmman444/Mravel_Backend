package com.mravel.catalog.exception;

import com.mravel.common.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;

public class CatalogException extends BaseException {

    public CatalogException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }

    public CatalogException(String message, @NonNull HttpStatus status) {
        super(message, status);
    }
}

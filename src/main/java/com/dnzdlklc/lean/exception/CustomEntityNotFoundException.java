package com.dnzdlklc.lean.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by denizdalkilic on 24/07/2020 @ 18:03.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomEntityNotFoundException extends RuntimeException{
    public CustomEntityNotFoundException(String message) {
        super(message);
    }
}

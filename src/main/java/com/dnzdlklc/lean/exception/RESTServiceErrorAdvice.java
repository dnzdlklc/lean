package com.dnzdlklc.lean.exception;

import com.dnzdlklc.lean.model.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.*;

/**
 * Created by denizdalkilic on 24/07/2020 @ 17:57.
 */
@Slf4j
@ControllerAdvice
public class RESTServiceErrorAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<?> handleRunTimeException(RuntimeException e) {
        return error(INTERNAL_SERVER_ERROR, e);
    }

    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<?> handleAuthException(BadCredentialsException e) {
        return error(UNAUTHORIZED, e);
    }

    @ExceptionHandler({CustomEntityNotFoundException.class})
    public ResponseEntity<?> handleNotFoundException(CustomEntityNotFoundException e) {
        return error(NOT_FOUND, e);
    }

    private ResponseEntity<?> error(HttpStatus status, Exception e) {
        log.error("Exception : ", e);
        return ResponseEntity.status(status).body(new GenericResponse(status.value(), e.getMessage(), false));
    }
}

package com.samsung.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorHttpResponse handleAllException(Exception ex) {
        return ErrorHttpResponse.builder()
                .dateTime(LocalDateTime.now())
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(ex.getMessage())
                .build();
    }
}

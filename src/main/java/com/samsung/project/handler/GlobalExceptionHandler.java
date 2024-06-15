package com.samsung.project.handler;

import com.samsung.project.exception.ApprovalException;
import com.samsung.project.exception.ErrorCode;
import com.samsung.project.response.AppResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ResponseEntity<AppResponse<?>> handleRestException(Exception ex) {
        AppResponse<?> appResponse = AppResponse.builder()
                .timestamp(LocalDateTime.now())
                .code(ErrorCode.UNCATEGORIZED.getCode())
                .message(ErrorCode.UNCATEGORIZED.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
        return ResponseEntity.internalServerError().body(appResponse);
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<AppResponse<?>> handleValidationException(BindException ex) {
        String errorKey = ex.getAllErrors().get(0).getDefaultMessage();
        ErrorCode errorCode = ErrorCode.valueOf(errorKey);
        AppResponse<?> appResponse = AppResponse.builder()
                .timestamp(LocalDateTime.now())
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .status(errorCode.getHttpStatus())
                .build();
        return ResponseEntity.badRequest().body(appResponse);
    }
    @ExceptionHandler(ApprovalException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<AppResponse<?>> handleAppException(ApprovalException ex) {
        AppResponse<?> appResponse = AppResponse.builder()
                .timestamp(LocalDateTime.now())
                .code(ex.getErrorCode().getCode())
                .message(ex.getErrorCode().getMessage())
                .status(ex.getErrorCode().getHttpStatus())
                .build();
        return ResponseEntity.badRequest().body(appResponse);
    }
}

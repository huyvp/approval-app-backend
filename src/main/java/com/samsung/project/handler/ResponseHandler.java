package com.samsung.project.handler;

import com.samsung.project.response.AppResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public class ResponseHandler {
    public static ResponseEntity<?> execute(Object result) {
        AppResponse<?> appResponse = AppResponse.builder()
                .timestamp(LocalDateTime.now())
                .code(2000)
                .message("success")
                .status(HttpStatus.OK)
                .result(result)
                .build();
        return ResponseEntity.ok(appResponse);
    }
}

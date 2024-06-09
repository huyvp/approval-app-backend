package com.samsung.project.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorHttpResponse {
    @JsonFormat(pattern = "HH:mm:ss yyyy-MM-dd")
    private LocalDateTime dateTime;
    private HttpStatus httpStatus;
    private int statusCode;
    private String message;
}

package com.samsung.project.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

import static com.samsung.project.constant.Constants.Pattern.TIME;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppResponse<T> {
    @JsonFormat(pattern = TIME)
    private LocalDateTime timestamp;
    private int code;
    private HttpStatus status;
    private String message;
    private T result;
}

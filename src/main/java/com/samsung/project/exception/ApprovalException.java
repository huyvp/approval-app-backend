package com.samsung.project.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApprovalException extends RuntimeException{
    private ErrorCode errorCode;

    public ApprovalException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}

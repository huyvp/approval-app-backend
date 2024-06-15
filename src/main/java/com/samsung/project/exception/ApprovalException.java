package com.samsung.project.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApprovalException extends RuntimeException{
    private ErrorCode errorCode;

    public ApprovalException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}

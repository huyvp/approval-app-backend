package com.samsung.project.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorCode {
    UNCATEGORIZED(5000, "Uncategorized Exception"),
    UNAUTHORIZED(4000, "Username or password is not correct"),

    // VALIDATION
    INVALID_ID(1000, "Invalid id, id must be more than 0"),
    INVALID_REQUEST_PURPOSE(1001, "Purpose must be between 1 and 20 characters"),
    INVALID_REQUEST_NOTE(1002, "Purpose must be between 1 and 200 characters"),

    INVALID_USER_USERNAME(1003, "Username name is require!"),
    INVALID_USER_PASSWORD(1004, "Password is require!"),
    INVALID_USER_EMAIL(1005, "Email format incorrect!"),
    ;
    private int code;
    private String message;
}

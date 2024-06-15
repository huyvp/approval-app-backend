package com.samsung.project.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorCode {
    UNCATEGORIZED(500, HttpStatus.INTERNAL_SERVER_ERROR, "Uncategorized Exception"),
    // ----------------------------------
    // Related to AUTHORITY
    // ----------------------------------
    AUTH401(401, HttpStatus.FORBIDDEN, "Forbidden"),
    AUTH402(402, HttpStatus.UNAUTHORIZED, "Username or password is not correct"),
    // ----------------------------------
    // Related to USER
    // ----------------------------------
    APP301(301, HttpStatus.BAD_REQUEST, "User existed"),
    APP302(302, HttpStatus.BAD_REQUEST, "User not found"),
    // ----------------------------------
    // Related to VALIDATION
    // ----------------------------------
    VALID100(100, HttpStatus.BAD_REQUEST, "Invalid id, id must be more than 0"),
    VALID101(101, HttpStatus.BAD_REQUEST, "Purpose must be between 1 and 20 characters"),
    VALID102(102, HttpStatus.BAD_REQUEST, "Note must be between 1 and 200 characters"),

    VALID103(103, HttpStatus.BAD_REQUEST, "Username name is require!"),
    VALID104(104, HttpStatus.BAD_REQUEST, "Password is require!"),
    VALID105(105, HttpStatus.BAD_REQUEST, "Email format incorrect!"),
    ;
    private int code;
    private HttpStatus httpStatus;
    private String message;
}

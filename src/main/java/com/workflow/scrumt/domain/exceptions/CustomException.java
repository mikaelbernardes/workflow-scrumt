package com.workflow.scrumt.domain.exceptions;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {

    private final ExceptionLevel level;
    private final HttpStatus httpStatus;

    public CustomException(String message, ExceptionLevel level, HttpStatus httpStatus) {
        super(message);
        this.level = level;
        this.httpStatus = httpStatus;
    }

    public ExceptionLevel getLevel() {
        return level;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String toString() {
        return "{" +
                "message=" + getMessage() + '\'' +
                "level=" + level +
                '}';
    }
}

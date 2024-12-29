package com.workflow.scrumt.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Map<String, Object>> handleCustomException(CustomException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", ex.getMessage());
        response.put("level", ex.getLevel().name());

        HttpStatus status = ex.getHttpStatus() != null
                ? ex.getHttpStatus()
                : switch (ex.getLevel()) {
            case SUCCESS -> HttpStatus.OK;
            case INFO, NOTICE -> HttpStatus.BAD_REQUEST;
            case WARNING -> HttpStatus.FORBIDDEN;
            case ERROR, CRITICAL -> HttpStatus.INTERNAL_SERVER_ERROR;
        };

        return new ResponseEntity<>(response, status);
    }
}

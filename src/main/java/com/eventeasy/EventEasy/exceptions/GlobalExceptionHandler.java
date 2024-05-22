package com.eventeasy.EventEasy.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<SimpleResponse> handProductNotFoundException(BaseException baseException){
        return ResponseEntity.status(baseException.getHttpStatus()).body(baseException.getSimpleResponse());
    }
}

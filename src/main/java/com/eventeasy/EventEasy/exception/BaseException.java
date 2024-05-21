package com.eventeasy.EventEasy.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
@AllArgsConstructor
public abstract class BaseException extends RuntimeException{
    private HttpStatus httpStatus;
    private String message;
}


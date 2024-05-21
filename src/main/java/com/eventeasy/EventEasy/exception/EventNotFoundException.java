package com.eventeasy.EventEasy.exception;

import org.springframework.http.HttpStatus;

public class EventNotFoundException extends BaseException{
    public EventNotFoundException(HttpStatus httpStatus, String message) {
        super(httpStatus, message);
    }
}

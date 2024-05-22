package com.eventeasy.EventEasy.exceptions;

import org.springframework.http.HttpStatus;

public class EventNotFoundException extends BaseException{
    public EventNotFoundException(HttpStatus httpStatus, SimpleResponse response) {
        super(httpStatus, response);
    }
}

package com.eventeasy.EventEasy.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
@AllArgsConstructor
public class BaseException extends RuntimeException{
    private HttpStatus httpStatus;
    private SimpleResponse simpleResponse;
}


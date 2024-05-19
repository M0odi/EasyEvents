package com.eventeasy.EventEasy;

import org.springframework.http.ResponseEntity;

public interface Command <E,T>{
    ResponseEntity<?> execute(E entity);
}

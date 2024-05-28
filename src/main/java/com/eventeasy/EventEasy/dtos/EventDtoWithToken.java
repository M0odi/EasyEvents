package com.eventeasy.EventEasy.dtos;

import lombok.Data;

import java.time.LocalDate;
@Data
public class EventDtoWithToken {
    private String name;
    private String description;
    private LocalDate dateOfEvent;
    private String token;
}

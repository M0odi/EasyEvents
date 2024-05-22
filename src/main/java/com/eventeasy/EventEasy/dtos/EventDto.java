package com.eventeasy.EventEasy.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@Builder
public class EventDto {
    private String name;
    private String description;
    private LocalDate dateOfEvent;
}

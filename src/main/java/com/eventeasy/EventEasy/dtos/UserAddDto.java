package com.eventeasy.EventEasy.dtos;

import com.eventeasy.EventEasy.models.Event;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserAddDto {
    private Integer event_id;
    private String email;
}

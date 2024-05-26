package com.eventeasy.EventEasy.controllers;


import com.eventeasy.EventEasy.config.JwtService;
import com.eventeasy.EventEasy.dtos.EventDto;
import com.eventeasy.EventEasy.dtos.UserAddDto;
import com.eventeasy.EventEasy.repositories.EventRepository;
import com.eventeasy.EventEasy.services.EventService;
import com.eventeasy.EventEasy.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class EventController {

    private final EventService eventService;
    private final UserService userService;
    private final JwtService jwtService;
    private final EventRepository eventRepository;

    @PostMapping("/secured/create-event")
    public ResponseEntity<?> createEvent(
            @RequestBody EventDto eventDto
    ) {
        eventService.createEvent(eventDto.getName(), eventDto.getDescription(), eventDto.getDateOfEvent());
        return ResponseEntity.accepted().build();
    }


    @PostMapping("/secured/event-list")
    public ResponseEntity<List<EventDto>> listEvents(
            @RequestBody String email
    ) {
       /* var user = userService.getUserByEmail(email);
        return user.map(value -> ResponseEntity.ok(value.getMemberEventList().stream().map(
                x -> EventDto.builder().dateOfEvent(x.getDateOfEvent()).description(x.getDescription()).name(x.getName()).build()
        ).toList())).orElseGet(() -> ResponseEntity.badRequest().build());*/
        return ResponseEntity.ok(eventService.getAllEvents().stream()
                .map(x -> EventDto.builder()
                        .name(x.getName())
                        .description(x.getDescription())
                        .dateOfEvent(x.getDateOfEvent())
                        .build())
                .toList());

    }
    @GetMapping("")
    public String mainPage(){
        return "main-page";
    }


    @DeleteMapping("/secured/remove-event")
    public ResponseEntity<?> removeEventById(
            @RequestBody Integer id
    ) {
        return eventService.remove(id) ? ResponseEntity.status(200).build() : ResponseEntity.badRequest().build();
    }

    @PutMapping("/secured/add-member")
    public ResponseEntity<?> addMember(
            @RequestBody UserAddDto userAddDto
    ) {
        var user = userService.getUserByEmail(userAddDto.getEmail());
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(eventService.getEvent(userAddDto.getEvent_id()).getMembers().add(user.get()));
    }

    @PutMapping("/secured/add-organizer")
    public ResponseEntity<?> addOrganizer(
            @RequestBody UserAddDto userAddDto
    ) {
        var user = userService.getUserByEmail(userAddDto.getEmail());
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(eventService.getEvent(userAddDto.getEvent_id()).getOrganizers().add(user.get()));
    }


}

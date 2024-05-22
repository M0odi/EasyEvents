package com.eventeasy.EventEasy.controllers;



import com.eventeasy.EventEasy.dtos.EventDto;
import com.eventeasy.EventEasy.dtos.UserDto;
import com.eventeasy.EventEasy.services.EventService;
import com.eventeasy.EventEasy.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@AllArgsConstructor
public class EventController {

    private final EventService eventService;
    private final UserService userService;

    @PostMapping("/secured/create-event")
    public ResponseEntity<?> createEvent(
            @RequestBody EventDto eventDto
    ) {
        eventService.createEvent(eventDto.getName(), eventDto.getDescription(), eventDto.getDateOfEvent());
        return ResponseEntity.accepted().build();
    }
/*
    @GetMapping("/secured/create-event-template")
    public String createEventTemplate(@AuthenticationPrincipal User user) {
        return "create-event-template";
    }*/

    @GetMapping("/secured/event-list")
    public ResponseEntity<List<EventDto>> listEvents(@RequestBody UserDto userDto) {
        var user = userService.getUserByEmail(userDto.getEmail());
        user.ifPresent(value -> ResponseEntity.ok((eventService.getAllEventsByUserId(value.getId()))));
        return ResponseEntity.ok(Collections.emptyList());
    }


    @DeleteMapping("/secured/remove-event/{id}")
    public String removeEventById(
            @PathVariable Integer id
    ) {
        eventService.remove(id);
        return "list-events";
    }

    @GetMapping("")
    public String greeting() {
        return "main-page";
    }
    @PutMapping("/secured/add-member")
    public String addMember(@RequestParam (name = "event_id")Integer eventId, @RequestParam (name = "email")String email){
        eventService.addMember(eventId,email);
        return "member was addwed";
    }
    @PutMapping("/secured/add-organizer")
    public String addOrganizer(@RequestParam (name = "event_id")Integer eventId, @RequestParam (name = "email")String email){
        eventService.addMember(eventId,email);
        return "organizers was addwed";
    }


}

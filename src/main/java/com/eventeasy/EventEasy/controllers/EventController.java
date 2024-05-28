package com.eventeasy.EventEasy.controllers;


import com.eventeasy.EventEasy.auth.AuthenticationResponse;
import com.eventeasy.EventEasy.config.JwtService;
import com.eventeasy.EventEasy.dtos.EventDto;
import com.eventeasy.EventEasy.dtos.EventDtoWithToken;
import com.eventeasy.EventEasy.dtos.UserAddDto;
import com.eventeasy.EventEasy.models.Event;
import com.eventeasy.EventEasy.models.User;
import com.eventeasy.EventEasy.repositories.EventRepository;
import com.eventeasy.EventEasy.services.EventService;
import com.eventeasy.EventEasy.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@AllArgsConstructor
public class EventController {

    private final EventService eventService;
    private final UserService userService;
    private final JwtService jwtService;
    private final EventRepository eventRepository;

    @PostMapping("/secured/create-event")
    public ResponseEntity<?> createEvent(@RequestBody EventDtoWithToken eventDto
    ) {
        try {
            String email = jwtService.extractUserName(eventDto.getToken());
            Optional<User> userOptional = userService.getUserByEmail(email);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                Event event = Event.builder()
                        .name(eventDto.getName())
                        .dateOfEvent(eventDto.getDateOfEvent())
                        .dateOfCreate(eventDto.getDateOfEvent())
                        .members(Set.of(user))
                        .organizers(Set.of(user))
                        .build();

                eventService.createEvent(event);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PostMapping("/secured/event-list")
    public ResponseEntity<List<EventDto>> listEvents(
            @RequestBody AuthenticationResponse token
    ) {
        var email = jwtService.extractUserName(token.getToken());
        return ResponseEntity.ok(eventService.getAllEventsByUserEmail(email).stream().map(
                x -> EventDto.builder().name(x.getName()).id(x.getId()).dateOfEvent(x.getDateOfEvent()).description(x.getDescription()).build()
        ).toList());
    }

    @PutMapping("/secured/event-update")
    public ResponseEntity<?> eventUpdate(
            @RequestBody Event event
    ) {
        eventService.save(event);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/secured/remove-event")
    public ResponseEntity<?> removeEventById(
            @RequestBody Integer id
    ) {
        return eventService.remove(id) ? ResponseEntity.status(200).build() : ResponseEntity.badRequest().build();
    }
    @GetMapping("/secured/event/{id}")
    public ResponseEntity<EventDto> getEventById(@PathVariable Integer id){
        eventService.getEvent(id);
        return ResponseEntity.ok(eventService.getEventDto(id));
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

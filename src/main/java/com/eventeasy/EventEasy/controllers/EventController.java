package com.eventeasy.EventEasy.controllers;



import com.eventeasy.EventEasy.services.EventService;
import com.eventeasy.EventEasy.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@Controller
@AllArgsConstructor
public class EventController {

    private final EventService eventService;
    private final UserService userService;

    @PostMapping("/secured/create-event")
    public String createEvent(@RequestParam(name = "name") String name,
                              @RequestParam(name = "description") String description,
                              @RequestParam(name = "date_of_event") LocalDate dateOfEvent,
                              Map<String, Object> model) {

        eventService.createEvent(name, description, dateOfEvent);
        model.put("events", eventService.getAllEvents());
        return "list-events";
    }

    @GetMapping("/secured/create-event-template")
    public String createEventTemplate() {
        return "create-event-template";
    }

    @GetMapping("/secured/event-list")
    public String listEvents(Map<String, Object> model) {
        model.put("events", eventService.getAllEvents());
        return "list-events";
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

package com.eventeasy.EventEasy.controllers;

import com.eventeasy.EventEasy.models.Event;
import com.eventeasy.EventEasy.services.EventService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Map;

@Controller
@AllArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping("/create-event")
    public String createEvent(@RequestParam(name = "name") String name,
                              @RequestParam(name = "description") String description,
                              @RequestParam(name = "date_of_event") LocalDate dateOfEvent,
                              Map<String, Object> model) {

        Event event = new Event();
        event.setName(name);
        event.setDescription(description);
        event.setDateOfEvent(dateOfEvent);

        eventService.save(event);

        model.put("events", eventService.getAllEvents());

        return "list-events";
    }

    @GetMapping("/create-event-template")
    public String createEventTemplate() {
        return "create-event-template";
    }

    @GetMapping("/list-events")
    public String listEvents(Map<String, Object> model) {
        model.put("events", eventService.getAllEvents());
        return "list-events";
    }

}

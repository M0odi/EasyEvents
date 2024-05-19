package com.eventeasy.EventEasy.controllers;

import com.eventeasy.EventEasy.models.Event;
import com.eventeasy.EventEasy.services.EventService;
import lombok.AllArgsConstructor;
import org.apache.catalina.valves.rewrite.InternalRewriteMap;
import org.hibernate.persister.entity.SingleTableEntityPersister;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@Controller
@AllArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping("secured/create-event")
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

    @GetMapping("secured/create-event-template")
    public String createEventTemplate() {
        return "create-event-template";
    }

    @GetMapping("/auth/list-events")
    public String listEvents(Map<String, Object> model) {
        model.put("events", eventService.getAllEvents());
        return "list-events";
    }
    @DeleteMapping("secured/remove-event/{id}")
    public String removeEventById(
            @PathVariable Integer id
    ){
        eventService.remove(id);
        return "list-events";
    }

    @GetMapping("/")
    public String index() {
        return "redirect: greeting";
    }
    @GetMapping("/greeting")
    public String greeting() {
        return "main-page";
    }

}
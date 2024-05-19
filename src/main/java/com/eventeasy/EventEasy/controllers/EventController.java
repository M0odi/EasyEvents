package com.eventeasy.EventEasy.controllers;

import com.eventeasy.EventEasy.models.Event;
import com.eventeasy.EventEasy.services.EventService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    @GetMapping("/auth/main")
    public String index() {
<<<<<<< HEAD
         return "redirect: greeting";
    }
<<<<<<< HEAD
    @GetMapping("/auth/greeting")
    public ModelAndView welcome() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main-page");
        return modelAndView;
=======
    @GetMapping("/greeting")
    public String greeting() {
        return "main-page";
=======
         return "main-page";
>>>>>>> 5f17c76 (fed)
>>>>>>> fabc36435b5583ae47d65b8d11173267f73a9520
    }


}

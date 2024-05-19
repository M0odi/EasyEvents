package com.eventeasy.EventEasy.controllers;

import com.eventeasy.EventEasy.models.Event;
import com.eventeasy.EventEasy.services.EventService;
import com.eventeasy.EventEasy.services.UserService;
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
    private final UserService userService;

    @PostMapping("/secured/create-event")
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

    @GetMapping("/secured/create-event-template")
    public String createEventTemplate() {
        return "create-event-template";
    }

    @GetMapping("/secured/list-events")
    public String listEvents(Map<String, Object> model) {
        var allEvents = eventService.getAllEvents();
        allEvents.forEach(e ->  e.setAmount(e.getOrganizers().size() + e.getMembers().size()));
        model.put("events", allEvents);

        return "list-events";
    }
    @PostMapping("/secured/add-member/{email}")
    public String addMember(@PathVariable String email, @PathVariable Integer eventId){
        eventService.getEvent(eventId).getMembers().add(userService.getUserByEmail(email).orElseThrow());
        return "list-events";
    }
    @DeleteMapping("/secured/remove-event/{id}")
    public String removeEventById(
            @PathVariable Integer id
    ) {
        eventService.remove(id);
        return "list-events";
    }

    @GetMapping("/signing")
    public ModelAndView signing() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("log-page");
        return modelAndView;
    }

    @GetMapping("/reg")
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("reg-page");
        return modelAndView;
    }

    @GetMapping("/auth/main-page")
    public ModelAndView welcome() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main-page");
        return modelAndView;
    }

    @GetMapping("/greeting")
    public String greeting() {
        return "main-page";
    }


}

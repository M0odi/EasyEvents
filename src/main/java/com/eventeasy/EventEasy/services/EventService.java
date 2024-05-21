package com.eventeasy.EventEasy.services;

import com.eventeasy.EventEasy.exception.EventNotFoundException;
import com.eventeasy.EventEasy.models.Event;
import com.eventeasy.EventEasy.models.User;
import com.eventeasy.EventEasy.repositories.EventRepository;
import com.eventeasy.EventEasy.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final UserService userService;

    public void createEvent(String name, String description, LocalDate dateOfEvent){
        Event event = new Event();
        event.setName(name);
        event.setDescription(description);
        event.setDateOfEvent(dateOfEvent);


        eventRepository.save(event);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEvent(int id) {
        return eventRepository.findById(id);
    }

    public void save(Event event) {
        eventRepository.save(event);
    }

    public void remove(int id) {
        eventRepository.deleteById(id);
    }
    public void addMember(Integer event_id,String email){
        Event event = eventRepository.findEventById(event_id);
        if (event != null) {
            event.getMembers().add(userService.getUserByEmail(email).get());
            eventRepository.save(event);
        } else {
            throw new EventNotFoundException(HttpStatus.BAD_REQUEST,"Event with ID " + event_id + " not found");
        }
    }
    public void addOrganizer(Integer event_id,String email){
        Event event = eventRepository.findEventById(event_id);
        if (userService.getUserByEmail(email).isEmpty()){
            throw new UsernameNotFoundException("User with email" + "not found");
        }
        if (event != null) {
            event.getOrganizers().add(userService.getUserByEmail(email).get());
            eventRepository.save(event);
        } else {
            throw new EventNotFoundException(HttpStatus.BAD_REQUEST,"Event with ID " + event_id + " not found");
        }
    }


}

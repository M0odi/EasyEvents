package com.eventeasy.EventEasy.services;

import com.eventeasy.EventEasy.dtos.EventDto;
import com.eventeasy.EventEasy.exceptions.EventNotFoundException;
import com.eventeasy.EventEasy.exceptions.SimpleResponse;
import com.eventeasy.EventEasy.models.Event;
import com.eventeasy.EventEasy.repositories.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final UserService userService;

    public void createEvent(Event event){
        eventRepository.save(event);
    }
    public List<Event> getAllEvents(){
        return eventRepository.findAll();
    }
    public List<Event> getAllEventsByUserEmail(String email) {
        return eventRepository.findAllEventUser(email);
    }

    public Event getEvent(int id) {
        return eventRepository.findById(id);
    }
    public EventDto getEventDto(int id) {
        return eventRepository.getEventById(id);
    }

    public void save(Event event) {
        eventRepository.save(event);
    }

    public boolean remove(int id) {
        return  eventRepository.readEventById(id);
    }
    public void addMember(Integer event_id,String email){
        Event event = eventRepository.findEventById(event_id);
        if (event != null) {
            event.getMembers().add(userService.getUserByEmail(email).get());
            eventRepository.save(event);
        } else {
            throw new EventNotFoundException(HttpStatus.BAD_REQUEST, SimpleResponse.builder().message("Event with ID " + event_id + " not found").build());
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
            throw new EventNotFoundException(HttpStatus.BAD_REQUEST, SimpleResponse.builder().message("Event with ID " + event_id + " not found").build());
        }
    }


}

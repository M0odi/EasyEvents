package com.eventeasy.EventEasy.services;

import com.eventeasy.EventEasy.models.Event;
import com.eventeasy.EventEasy.repositories.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

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

}
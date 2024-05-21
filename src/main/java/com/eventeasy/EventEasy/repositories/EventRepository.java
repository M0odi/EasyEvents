package com.eventeasy.EventEasy.repositories;

import com.eventeasy.EventEasy.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

    Event findById(int id);

    Event findEventById(Integer eventId);
}

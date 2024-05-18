package com.eventeasy.EventEasy.repositories;

import com.eventeasy.EventEasy.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {

    Event findById(int id);

}

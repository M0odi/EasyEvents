package com.eventeasy.EventEasy.repositories;

import com.eventeasy.EventEasy.dtos.EventDto;
import com.eventeasy.EventEasy.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
    @Query("SELECT e FROM Event e JOIN e.members Join e.organizers m WHERE m.email = :email")
    List<Event> findAllEventUser(@Param("email") String email);
    Event findById(int id);
    EventDto getEventById(int id);
    boolean readEventById(int id);
    Event findEventById(Integer eventId);

}

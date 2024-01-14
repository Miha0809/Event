package com.slamy.app.repositories;

import com.slamy.app.models.Event;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EventRepository extends CrudRepository<Event, Long> {
    // List<IEvent> getEvents();
    // IEvent getEventById(Long id);
}

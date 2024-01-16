package com.slamy.app.controllers;


import com.slamy.app.models.Event;
import com.slamy.app.models.User;
import com.slamy.app.repositories.EventRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public")
public class EventController {
    private final EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @GetMapping("/events")
    public List<Event> getEvents() {
        return this.eventRepository.getEventsBy();
    }

    @GetMapping("/event_by_id/{id}")
    public Event getEventById(@PathVariable("id") Long id) throws Exception {
        try {
            return this.eventRepository.getEventById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @GetMapping("/users_current_event/{id}")
    public List<User> getUsersRegisteredCurrentEvent(@PathVariable("id") Long id) throws Exception {
        try {
            Event event = this.eventRepository.getEventById(id);

            return event.getUsers();
//            return null;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
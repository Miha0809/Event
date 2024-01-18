package com.slamy.app.controllers;

import com.slamy.app.models.Email;
import com.slamy.app.models.Event;
import com.slamy.app.models.User;
import com.slamy.app.repositories.EmailRepository;
import com.slamy.app.repositories.EventRepository;
import com.slamy.app.repositories.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping(path = "api/info")
public class InfoController {
    private final UserRepository userRepository;
    private final EmailRepository emailRepository;
    private final EventRepository eventRepository;

    public InfoController(UserRepository userRepository, EmailRepository emailRepository, EventRepository eventRepository) {
        this.userRepository = userRepository;
        this.emailRepository = emailRepository;
        this.eventRepository = eventRepository;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return this.userRepository.findAllBy();
    }

    @GetMapping("/emails")
    public List<Email> getEmails() {
        return this.emailRepository.findAllBy();
    }

    @GetMapping("/events")
    public List<Event> getEvents() {
        return this.eventRepository.findAllBy();
    }
}

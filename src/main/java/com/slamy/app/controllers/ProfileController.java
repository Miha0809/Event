package com.slamy.app.controllers;

import com.slamy.app.models.Email;
import com.slamy.app.models.Event;
import com.slamy.app.models.User;
import com.slamy.app.repositories.EmailRepository;
import com.slamy.app.repositories.EventRepository;
import com.slamy.app.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/public/profile")
public class ProfileController {
    private final UserRepository userRepository;
    private final EmailRepository emailRepository;
    private final EventRepository eventRepository;

    public ProfileController(UserRepository userRepository, EmailRepository emailRepository, EventRepository eventRepository) {
        this.userRepository = userRepository;
        this.emailRepository = emailRepository;
        this.eventRepository = eventRepository;
    }

    @GetMapping("/user_info")
    public User getInfoAboutUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Email email = this.emailRepository.findByName(authentication.getName());
        User user = this.userRepository.findByEmail(email);

        return user;
    }

    @DeleteMapping("/delete")
    public String deleteProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        try {
            Email email = this.emailRepository.findByName(authentication.getName());
            User user = this.userRepository.findByEmail(email);

            this.userRepository.delete(user);

            return "Removed is success!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @PostMapping("/register_to_event/{id}")
    public List<Event> registerToEvent(@PathVariable("id") Long id) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        try {
            Email email = this.emailRepository.findByName(authentication.getName());
            User user = this.userRepository.findByEmail(email);
            Event event = this.eventRepository.getEventById(id);

            if (!user.getEvents().contains(event)) {
                user.getEvents().add(event);
                this.userRepository.save(user);
            }

            return myRegisteredEvents();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @GetMapping("/my_registered_events")
    public List<Event> myRegisteredEvents() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        try {
            Email email = this.emailRepository.findByName(authentication.getName());
            User user = this.userRepository.findByEmail(email);

            return user.getEvents();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}

package com.slamy.app.controllers;

import com.slamy.app.models.Email;
import com.slamy.app.models.Event;
import com.slamy.app.models.Role;
import com.slamy.app.models.User;
import com.slamy.app.repositories.EmailRepository;
import com.slamy.app.repositories.EventRepository;
import com.slamy.app.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final UserRepository userRepository;
    private final EmailRepository emailRepository;
    private final PasswordEncoder passwordEncoder;
    private final EventRepository eventRepository;

    public AdminController(UserRepository userRepository, EmailRepository emailRepository, PasswordEncoder passwordEncoder, EventRepository eventRepository) {
        this.userRepository = userRepository;
        this.emailRepository = emailRepository;
        this.passwordEncoder = passwordEncoder;
        this.eventRepository = eventRepository;
    }

    @PostMapping("/register_user")
    public User registerUser(@RequestBody User user) throws Exception {
        try {
            Email email = user.getEmail();

            if (email != null) {
                user.setRole(Role.ROLE_USER);
                emailRepository.save(email);
            }

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            this.userRepository.save(user);

            return user;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @PostMapping("/add_event")
    public Event addEvent(@RequestBody Event event) throws Exception {
        try {
            System.out.println(event);
            this.eventRepository.save(event);

            return event;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @PostMapping("/delete_event/{id}")
    public String deleteEvent(@PathVariable("id") Long id) {
        try {
            // TODO: виписуватись із івента
            Event event = this.eventRepository.getEventById(id);
            this.eventRepository.delete(event);

            return "Success!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}

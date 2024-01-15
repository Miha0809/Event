package com.slamy.app.controllers;

import com.slamy.app.models.Email;
import com.slamy.app.models.User;
import com.slamy.app.repositories.EmailRepository;
import com.slamy.app.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final UserRepository userRepository;
    private final EmailRepository emailRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminController(UserRepository userRepository, EmailRepository emailRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.emailRepository = emailRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/add_user")
    public String addUser(@RequestBody User user) {
        try {
            Email email = user.getEmail();

            if (email != null) {
                emailRepository.save(email);
            }

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            this.userRepository.save(user);

            return user.toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}

package com.slamy.app.controllers;

import com.slamy.app.models.Email;
import com.slamy.app.models.User;
import com.slamy.app.repositories.EmailRepository;
import com.slamy.app.repositories.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping(path = "api/info")
public class InfoController {
    private UserRepository userRepository;
    private EmailRepository emailRepository;

    public InfoController(UserRepository userRepository, EmailRepository emailRepository) {
        this.userRepository = userRepository;
        this.emailRepository = emailRepository;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return this.userRepository.findAllBy();
    }

    @GetMapping("/emails")
    public List<Email> getEmails() {
        return this.emailRepository.findAllBy();
    }
}

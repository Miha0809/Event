package com.slamy.app.controllers;

import com.slamy.app.models.Email;
import com.slamy.app.models.User;
import com.slamy.app.repositories.EmailRepository;
import com.slamy.app.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/profile")
public class ProfileController {
    private UserRepository userRepository;
    private EmailRepository emailRepository;

    public ProfileController(UserRepository userRepository, EmailRepository emailRepository) {
        this.userRepository = userRepository;
        this.emailRepository = emailRepository;

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
}

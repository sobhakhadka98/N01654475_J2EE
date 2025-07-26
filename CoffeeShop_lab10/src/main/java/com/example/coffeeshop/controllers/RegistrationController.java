package com.example.coffeeshop.controllers;

import com.example.coffeeshop.models.User;
import com.example.coffeeshop.repository.UserRepository;
import com.example.coffeeshop.security.RegistrationForm;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;

    public RegistrationController(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("registrationForm", new RegistrationForm());
        return "registration";
    }

    @PostMapping
    public String processRegistration(@Valid @ModelAttribute("registrationForm") RegistrationForm form,
                                    BindingResult result) {
        if (result.hasErrors()) {
            return "registration";
        }

        // Check if username already exists
        if (userRepo.findByUsername(form.getUsername()) != null) {
            result.rejectValue("username", "error.registrationForm", "Username already exists");
            return "registration";
        }

        userRepo.save(form.toUser(passwordEncoder));
        return "redirect:/login";
    }
} 
package com.example.coffeeshop.controllers;

import com.example.coffeeshop.models.User;
import com.example.coffeeshop.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/legacy-home")
    public String getLegacyHomePage() {
        return "redirect:/";
    }
}

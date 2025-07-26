package com.example.coffeeshop.controllers;

import com.example.coffeeshop.models.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @GetMapping
    public String showOrders(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        return "orders";
    }
} 
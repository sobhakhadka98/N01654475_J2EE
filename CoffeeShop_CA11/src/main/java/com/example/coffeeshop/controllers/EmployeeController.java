package com.example.coffeeshop.controllers;

import com.example.coffeeshop.models.Employee;
import com.example.coffeeshop.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping("/employees")
    public String home(Model model) {
        model.addAttribute("employees", service.getAllEmployees());
        return "employeeList";
    }

    @GetMapping("/employee/add")
    public String showAddForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "form";
    }

    @PostMapping("/employee/save")
    public String saveEmployee(@Valid @ModelAttribute("employee") Employee employee,
                               BindingResult result,
                               Model model) {
        if (result.hasErrors()) {
            return "form";
        }
        service.saveEmployee(employee);
        return "redirect:/employees";
    }


    @GetMapping("/employee/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        model.addAttribute("employee", service.getEmployeeById(id));
        return "form";
    }

    @GetMapping("/employee/delete/{id}")
    public String deleteEmployee(@PathVariable int id) {
        service.deleteEmployee(id);
        return "redirect:/";
    }
}

package com.example.coffeeshop.security;

import com.example.coffeeshop.models.Gender;
import com.example.coffeeshop.models.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {
    
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String username;
    
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;
    
    @NotBlank(message = "Full name is required")
    private String fullName;
    
    @NotBlank(message = "Email is required")
    private String email;
    
    @NotBlank(message = "Street address is required")
    private String street;
    
    @NotBlank(message = "City is required")
    private String city;
    
    @NotBlank(message = "State is required")
    private String state;
    
    @NotBlank(message = "ZIP code is required")
    private String zip;
    
    @NotBlank(message = "Phone number is required")
    private String phone;
    
    @NotNull(message = "Gender is required")
    private Gender gender;
    
    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(
                null,
                username,
                passwordEncoder.encode(password),
                email,
                gender,
                fullName,
                street,
                city,
                state,
                zip,
                phone
        );
    }
} 
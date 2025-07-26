package com.example.coffeeshop.services;

import com.example.coffeeshop.models.User;
import com.example.coffeeshop.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;

    public void addUser(User user) {
        logger.info("Adding a new user: {}", user.getUsername());
        userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        logger.info("Fetching user by username: {}", username);
        return Optional.ofNullable(userRepository.findByUsername(username));
    }

    public List<User> getAllUsers() {
        logger.info("Fetching all users");
        return (List<User>) userRepository.findAll();
    }
}

package com.example.audiolibrary.service;

import com.example.audiolibrary.model.User;
import com.example.audiolibrary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    public User login(String username, String password) throws Exception {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new Exception("User not found!"));
        if (user.getPassword().equals(password)) {
            return user;
        } else {
            throw new Exception("Invalid credentials!");
        }
    }

    public User register(String username, String password) throws Exception {
        if (userRepository.existsByUsername(username)) {
            throw new Exception("User already exists!");
        }
        User newUser = new User(username, password, false);
        return userRepository.save(newUser);
    }

    public void logout() {
        // Handle session termination
    }

    public User promoteUser(String username) throws Exception {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new Exception("User not found!"));
        user.setAdmin(true);
        return userRepository.save(user);
    }
}

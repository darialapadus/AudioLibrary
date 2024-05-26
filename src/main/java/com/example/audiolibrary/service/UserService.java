package com.example.audiolibrary.service;

import com.example.audiolibrary.model.User;
import com.example.audiolibrary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User register(String username, String password) throws Exception {
        if (userRepository.existsByUsername(username)) {
            throw new Exception("User with given username already exists!");
        }
        User newUser = new User(username, password);
        return userRepository.save(newUser);
    }

    public User login(String username, String password) throws Exception {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user.get();
        } else {
            throw new Exception("Invalid username or password!");
        }
    }
}

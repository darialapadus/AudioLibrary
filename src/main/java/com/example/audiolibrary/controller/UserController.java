package com.example.audiolibrary.controller;

import com.example.audiolibrary.model.User;
import com.example.audiolibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestParam String username, @RequestParam String password) {
        try {
            return userService.register(username, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @GetMapping("/login")
    public User loginUser(@RequestParam String username, @RequestParam String password) {
        try {
            return userService.login(username, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}

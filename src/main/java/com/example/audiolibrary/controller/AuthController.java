package com.example.audiolibrary.controller;

import com.example.audiolibrary.model.User;
import com.example.audiolibrary.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public User register(@RequestParam String username, @RequestParam String password) {
        try {
            return authService.register(username, password);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/login")
    public User login(@RequestParam String username, @RequestParam String password) {
        try {
            return authService.login(username, password);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("/logout")
    public String logout() {
        authService.logout();
        return "Logged out successfully";
    }

    @PostMapping("/promote")
    public User promote(@RequestParam String username) {
        try {
            return authService.promoteUser(username);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}

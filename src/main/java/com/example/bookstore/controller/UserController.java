package com.example.bookstore.controller;

import com.example.bookstore.dto.UserDTO;
import com.example.bookstore.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
        UserDTO savedUser = userService.registerUser(userDTO);
        return ResponseEntity.ok(Map.of("message", "User registered successfully.", "user", savedUser));
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyAccount(@RequestBody UserDTO userDTO) {
        boolean verified = userService.verifyAccount(userDTO);
        if (verified) {
            return ResponseEntity.ok(Map.of("message", "Account verified successfully."));
        }
        return ResponseEntity.badRequest().body(Map.of("error", "Invalid code or expired."));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserDTO userDTO) {
        boolean loggedIn = userService.loginUser(userDTO);
        if (loggedIn) {
            return ResponseEntity.ok(Map.of("message", "Login successful."));
        }
        return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials."));
    }
}

package com.example.bookstore.controller;

import com.example.bookstore.dto.UserDTO;
import com.example.bookstore.entities.User;
import com.example.bookstore.mapper.UserMapper;
import com.example.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.bookstore.dto.validation.AdvancedValidation;
import com.example.bookstore.dto.validation.ValidationOrder;

@RestController()
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody UserDTO userDTO) {
        User userToCreate = UserMapper.userDTO2User(userDTO);
        User createdUser = userService.create(userToCreate);
        return ResponseEntity.ok(UserMapper.user2UserDTO(createdUser));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        User userToLogin = UserMapper.userDTO2User(userDTO);
        User existentUser = userService.login(userToLogin);
        return ResponseEntity.ok(UserMapper.user2UserDTO(existentUser));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> verify(@PathVariable Long userId, @RequestBody UserDTO updatedUserDTO) {
        User userToUpdate = UserMapper.userDTO2User(updatedUserDTO);
        User updatedUser = userService.verify(userId, userToUpdate);
        return ResponseEntity.ok(UserMapper.user2UserDTO(updatedUser));
    }
}
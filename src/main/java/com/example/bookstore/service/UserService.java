package com.example.bookstore.service;


import com.example.bookstore.entities.User;
import com.example.bookstore.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.InputMismatchException;
import java.util.Random;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    public User create(User user) {
        if (user.getId() != null) {
            throw new RuntimeException("You cannot provide an ID to a new user that you want to create");
        }
        if (user.getVerificationCode() != null) {
            throw new RuntimeException("You cannot provide a verification code to a user");
        }

        user.setPassword(encodePassword(user.getPassword()));
        String verificationCode = generateVerificationCode();
        user.setVerificationCode(verificationCode);
        System.out.println(Thread.currentThread().getName());
        emailService.sendVerificationEmail(user.getEmail(), verificationCode);
        return userRepository.save(user);
    }

    public User verify(Long userId, User updatedUser) {
        if (!userRepository.findById(userId).isPresent()) {
            throw new EntityNotFoundException("User with ID " + userId + " not found");
        }

        User user = userRepository.findById(userId).get();
        if (user.getVerificationCode().equals(updatedUser.getVerificationCode())) {
            user.setVerified(true);
            user.setVerificationCode("done");
        }
        return userRepository.save(user);
    }

    public User login(User user) {
        User existentUser = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("User with email " + user.getEmail() + " not found"));

        String encodedPassword = encodePassword(user.getPassword());
        if (!existentUser.isVerified() || !encodedPassword.equals(existentUser.getPassword())) {
            throw new InputMismatchException();
        }
        return existentUser;
    }

    private String generateVerificationCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000); // Generate 6-digit code
        return String.valueOf(code);
    }

    private String encodePassword(String password) {
        String encodedPassword = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            encodedPassword = Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        return encodedPassword;
    }
}

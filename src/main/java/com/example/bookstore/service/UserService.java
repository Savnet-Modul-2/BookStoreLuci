package com.example.bookstore.service;

import com.example.bookstore.dto.UserDTO;
import com.example.bookstore.entities.User;
import com.example.bookstore.repository.UserRepository;
import com.example.bookstore.utils.PasswordHasher;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final EmailService emailService;

    public UserService(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    public UserDTO registerUser(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setYearOfBirth(userDTO.getYearOfBirth());
        user.setGender(userDTO.getGender());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setPassword(PasswordHasher.hash(userDTO.getPassword()));
        user.setCountry(userDTO.getCountry());

        user.generateVerificationCode();
        userRepository.save(user);

        emailService.sendVerificationEmail(user.getEmail(), user.getVerificationCode());

        return new UserDTO(user.getFirstName(), user.getLastName(), user.getEmail(), user.isVerifiedAccount());
    }

    public boolean verifyAccount(UserDTO userDTO) {
        Optional<User> userOpt = userRepository.findByEmail(userDTO.getEmail());
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (user.getVerificationCode().equals(userDTO.getVerificationCode()) &&
                    user.getCodeExpiry().isAfter(LocalDateTime.now())) {

                user.setVerifiedAccount(true);
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }

    public boolean loginUser(UserDTO userDTO) {
        Optional<User> userOpt = userRepository.findByEmail(userDTO.getEmail());
        return userOpt.map(user -> user.getPassword().equals(PasswordHasher.hash(userDTO.getPassword())))
                .orElse(false);
    }
}

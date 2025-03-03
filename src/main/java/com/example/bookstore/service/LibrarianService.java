package com.example.bookstore.service;

import com.example.bookstore.entities.Librarian;
import com.example.bookstore.repository.LibrarianRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.InputMismatchException;

@Service
public class LibrarianService {
    @Autowired
    private LibrarianRepository librarianRepository;

    public Librarian create(Librarian librarian) {
        if (librarian.getId() != null) {
            throw new RuntimeException("You cannot provide an ID to a new librarian that you want to create");
        }

        librarian.setPassword(encodePassword(librarian.getPassword()));
        return librarianRepository.save(librarian);
    }

    public Librarian login(Librarian librarian) {
        Librarian existentLibrarian = librarianRepository.findByEmail(librarian.getEmail())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Librarian with email " + librarian.getEmail() + " not found"));

        String encodedPassword = encodePassword(librarian.getPassword());
        if (!encodedPassword.equals(existentLibrarian.getPassword())) {
            throw new InputMismatchException();
        }
        return existentLibrarian;
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
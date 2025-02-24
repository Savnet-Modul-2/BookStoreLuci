package com.example.bookstore.service;

import com.example.bookstore.entities.Librarian;
import com.example.bookstore.repository.LibrarianRepository;
import com.example.bookstore.utils.PasswordHasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibrarianService {

    @Autowired
    private LibrarianRepository librarianRepository;

    public Librarian registerLibrarian(Librarian librarian) {
        librarian.setPassword(PasswordHasher.hash(librarian.getPassword()));
        return librarianRepository.save(librarian);
    }

    public Librarian login(String email, String password) {
        Librarian librarian = librarianRepository.findByEmail(email);
        if (librarian != null && PasswordHasher.checkPassword(password, librarian.getPassword())) {
            return librarian;
        }
        throw new RuntimeException("Invalid credentials");
    }

    public List<Librarian> getAllLibrarians() {
        return librarianRepository.findAll();
    }

    public Librarian getLibrarianById(Long id) {
        return librarianRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Librarian not found with id: " + id));
    }

    public Librarian updateLibrarian(Long id, Librarian librarianDetails) {
        Librarian librarian = getLibrarianById(id);
        librarian.setName(librarianDetails.getName());
        librarian.setEmail(librarianDetails.getEmail());
        librarian.setPassword(PasswordHasher.hash(librarianDetails.getPassword()));
        return librarianRepository.save(librarian);
    }

    public void deleteLibrarian(Long id) {
        librarianRepository.deleteById(id);
    }
}


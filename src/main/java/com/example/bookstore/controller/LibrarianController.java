package com.example.bookstore.controller;

import com.example.bookstore.entities.Librarian;
import com.example.bookstore.service.LibrarianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/librarian")
public class LibrarianController {

    @Autowired
    private LibrarianService librarianService;

    @PostMapping("/register")
    public Librarian register(@RequestBody Librarian librarian) {
        return librarianService.registerLibrarian(librarian);
    }

    @PostMapping("/login")
    public Librarian login(@RequestParam String email, @RequestParam String password) {
        return librarianService.login(email, password);
    }

    @GetMapping
    public List<Librarian> getAllLibrarians() {
        return librarianService.getAllLibrarians();
    }

    @GetMapping("/{id}")
    public Librarian getLibrarianById(@PathVariable Long id) {
        return librarianService.getLibrarianById(id);
    }

    @PutMapping("/{id}")
    public Librarian updateLibrarian(@PathVariable Long id, @RequestBody Librarian librarian) {
        return librarianService.updateLibrarian(id, librarian);
    }

    @DeleteMapping("/{id}")
    public void deleteLibrarian(@PathVariable Long id) {
        librarianService.deleteLibrarian(id);
    }
}


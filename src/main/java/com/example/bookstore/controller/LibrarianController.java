package com.example.bookstore.controller;

import com.example.bookstore.dto.LibrarianDTO;
import com.example.bookstore.entities.Librarian;
import com.example.bookstore.mapper.LibrarianMapper;
import com.example.bookstore.service.LibrarianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/librarians")
public class LibrarianController {
    @Autowired
    private LibrarianService librarianService;

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody LibrarianDTO librarianDTO) {
        Librarian librarianToCreate = LibrarianMapper.librarianDTO2Librarian(librarianDTO);
        Librarian createdLibrarian = librarianService.create(librarianToCreate);
        return ResponseEntity.ok(LibrarianMapper.librarian2LibrarianDTO(createdLibrarian));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LibrarianDTO librarianDTO) {
        Librarian librarianToLogin = LibrarianMapper.librarianDTO2LibrarianWithoutLibrary(librarianDTO);
        Librarian existentLibrarian = librarianService.login(librarianToLogin);
        return ResponseEntity.ok(LibrarianMapper.librarian2LibrarianDTOWithoutLibrary(existentLibrarian));
    }
}
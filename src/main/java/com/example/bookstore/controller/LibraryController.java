package com.example.bookstore.controller;

import com.example.bookstore.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/libraries")
public class LibraryController {
    @Autowired
    private LibraryService libraryService;
}
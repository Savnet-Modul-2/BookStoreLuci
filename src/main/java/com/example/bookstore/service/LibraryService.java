package com.example.bookstore.service;

import com.example.bookstore.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibraryService {
    @Autowired
    private LibraryRepository libraryRepository;
}
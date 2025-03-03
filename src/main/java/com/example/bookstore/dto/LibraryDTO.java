package com.example.bookstore.dto;

import java.util.ArrayList;
import java.util.List;

public class LibraryDTO {
    private Long id;
    private String name;
    private String adress;
    private String phoneNumber;
    private List<BookDTO> books = new ArrayList<>();
    private LibrarianDTO librarian;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<BookDTO> getBooks() {
        return books;
    }

    public void setBooks(List<BookDTO> books) {
        this.books = books;
    }

    public LibrarianDTO getLibrarian() {
        return librarian;
    }

    public void setLibrarian(LibrarianDTO librarian) {
        this.librarian = librarian;
    }
}
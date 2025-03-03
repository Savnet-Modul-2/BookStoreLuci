package com.example.bookstore.dto;

import com.example.bookstore.entities.Category;

import java.time.LocalDate;

public class BookDTO {
    private Long id;
    private String isbn;
    private String title;
    private String author;
    private LocalDate appearanceDate;
    private Integer nrOfPages;
    private Category category;
    private String language;
    private LibraryDTO library;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getAppearanceDate() {
        return appearanceDate;
    }

    public void setAppearanceDate(LocalDate appearanceDate) {
        this.appearanceDate = appearanceDate;
    }

    public Integer getNrOfPages() {
        return nrOfPages;
    }

    public void setNrOfPages(Integer nrOfPages) {
        this.nrOfPages = nrOfPages;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public LibraryDTO getLibrary() {
        return library;
    }

    public void setLibrary(LibraryDTO library) {
        this.library = library;
    }
}
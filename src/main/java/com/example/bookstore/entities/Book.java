package com.example.bookstore.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String isbn;
    private String title;
    private String author;
    private LocalDate appearanceDate;
    private int nrOfPages;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String language;

    @ManyToOne
    @JoinColumn(name = "library_id")
    private Library library;

    public Book() {}

    public Book(String isbn, String title, String author, LocalDate appearanceDate, int nrOfPages, Category category, String language, Library library) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.appearanceDate = appearanceDate;
        this.nrOfPages = nrOfPages;
        this.category = category;
        this.language = language;
        this.library = library;
    }

    public Long getId() {
        return id;
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

    public int getNrOfPages() {
        return nrOfPages;
    }

    public void setNrOfPages(int nrOfPages) {
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

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }
}

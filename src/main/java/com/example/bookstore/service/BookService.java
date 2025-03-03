package com.example.bookstore.service;

import com.example.bookstore.entities.Book;
import com.example.bookstore.entities.Library;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.LibraryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private LibraryRepository libraryRepository;

    public Book addBookToLibrary(Long libraryId, Book book) {
        if (book.getId() != null) {
            throw new RuntimeException("You cannot provide an ID to a new book that you want to create");
        }

        Library existentLibrary = libraryRepository.findById(libraryId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Library with id " + libraryId + " not found"));

        existentLibrary.addBook(book);
        book.setLibrary(existentLibrary);
        return bookRepository.save(book);
    }

    public void removeBookFromLibrary(Long libraryId, Long bookId) {
        Library existentLibrary = libraryRepository.findById(libraryId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Library with id " + libraryId + " not found"));

        Book existentBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Book with id " + bookId + " not found"));

        existentLibrary.removeBook(existentBook);
        existentBook.setLibrary(null);
        libraryRepository.save(existentLibrary);
    }

    public Book getBookById(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Book with id " + bookId + " not found"));
    }

    public Page<Book> searchBooks(String author, String title, Integer page, Integer size) {
        Pageable pageable = (page != null && size != null)
                ? PageRequest.of(page, size)
                : Pageable.unpaged();

        return bookRepository.findBooks(author, title, pageable);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book update(Long bookId, Book bookToUpdate) {
        return bookRepository.findById(bookId).map(existentBook -> {
            existentBook.setIsbn(bookToUpdate.getIsbn());
            existentBook.setTitle(bookToUpdate.getTitle());
            existentBook.setAuthor(bookToUpdate.getAuthor());
            existentBook.setAppearanceDate(bookToUpdate.getAppearanceDate());
            existentBook.setNrOfPages(bookToUpdate.getNrOfPages());
            existentBook.setCategory(bookToUpdate.getCategory());
            existentBook.setLanguage(bookToUpdate.getLanguage());
            //existentBook.setLibrary(bookToUpdate.getLibrary());
            return bookRepository.save(existentBook);
        }).orElseThrow(() -> new EntityNotFoundException("Book with ID " + bookId + " not found"));
    }

    public Page<Book> findAllPaginated(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }
}
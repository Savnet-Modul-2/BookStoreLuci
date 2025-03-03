package com.example.bookstore.controller;

import com.example.bookstore.dto.BookDTO;
import com.example.bookstore.entities.Book;
import com.example.bookstore.mapper.BookMapper;
import com.example.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController()
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("/add/{libraryId}")
    public ResponseEntity<?> addBookToLibrary(@PathVariable(name = "libraryId") Long libraryId,
                                              @RequestBody BookDTO bookDTO) {
        Book bookToCreate = BookMapper.bookDTO2BookWithoutLibrary(bookDTO);
        Book createdBook = bookService.addBookToLibrary(libraryId, bookToCreate);
        return ResponseEntity.ok(BookMapper.book2BookDTOWithoutLibrary(createdBook));
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<?> getBookById(@PathVariable(name = "bookId") Long bookId) {
        Book foundBook = bookService.getBookById(bookId);
        BookDTO bookDTO = BookMapper.book2BookDTOWithoutLibrary(foundBook);
        return ResponseEntity.ok(bookDTO);
    }

    @GetMapping()
    public ResponseEntity<?> findAll() {
        List<Book> books = bookService.findAll();
        List<BookDTO> bookDTOs = books.stream()
                .map(BookMapper::book2BookDTOWithoutLibrary)
                .toList();
        return ResponseEntity.ok(bookDTOs);
    }

    @GetMapping("/pagination")
    public ResponseEntity<?> findPaginated(Pageable pageable) {
        Page<Book> booksPage = bookService.findAllPaginated(pageable);
        Page<BookDTO> bookDTOPage = booksPage.map(BookMapper::book2BookDTOWithoutLibrary);
        return ResponseEntity.ok(bookDTOPage);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchBooks(@RequestParam(required = false) String bookAuthor,
                                         @RequestParam(required = false) String bookTitle,
                                         @RequestParam(required = false) Integer page,
                                         @RequestParam(required = false) Integer size) {
        Page<Book> booksPage = bookService.searchBooks(bookAuthor, bookTitle, page, size);
        Page<BookDTO> bookDTOPage = booksPage.map(BookMapper::book2BookDTOWithoutLibrary);

        return ResponseEntity.ok(bookDTOPage);
    }

    @PutMapping("/remove/{libraryId}/{bookId}")
    public ResponseEntity<?> removeBookFromLibrary(@PathVariable(name = "libraryId") Long libraryId,
                                                   @PathVariable(name = "bookId") Long bookId) {
        bookService.removeBookFromLibrary(libraryId, bookId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<?> update(@PathVariable Long bookId, @RequestBody BookDTO updatedBookDTO) {
        Book bookToUpdate = BookMapper.bookDTO2Book(updatedBookDTO);
        Book updatedBook = bookService.update(bookId, bookToUpdate);
        return ResponseEntity.ok(BookMapper.book2BookDTOWithoutLibrary(updatedBook));
    }
}
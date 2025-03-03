package com.example.bookstore.mapper;

import com.example.bookstore.dto.BookDTO;
import com.example.bookstore.entities.Book;

import java.util.List;

public class BookMapper {
    public static Book bookDTO2Book(BookDTO bookDTO) {
        Book book = new Book();
        book.setIsbn(bookDTO.getIsbn());
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setAppearanceDate(bookDTO.getAppearanceDate());
        book.setNrOfPages(bookDTO.getNrOfPages());
        book.setCategory(bookDTO.getCategory());
        book.setLanguage(bookDTO.getLanguage());
        if (bookDTO.getLibrary() != null) {
            book.setLibrary(LibraryMapper.libraryDTO2Library(bookDTO.getLibrary()));
        }
        return book;
    }

    public static BookDTO book2BookDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setIsbn(book.getIsbn());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setAppearanceDate(book.getAppearanceDate());
        bookDTO.setNrOfPages(book.getNrOfPages());
        bookDTO.setCategory(book.getCategory());
        bookDTO.setLanguage(book.getLanguage());
        if (book.getLibrary() != null) {
            bookDTO.setLibrary(LibraryMapper.library2LibraryDTO(book.getLibrary()));
        }
        return bookDTO;
    }

    public static List<Book> bookDTOs2Books(List<BookDTO> bookDTOs) {
        return bookDTOs.stream()
                .map(BookMapper::bookDTO2Book)
                .toList();
    }

    public static List<BookDTO> books2BookDTOs(List<Book> books) {
        return books.stream()
                .map(BookMapper::book2BookDTO)
                .toList();
    }

    public static Book bookDTO2BookWithoutLibrary(BookDTO bookDTO) {
        Book book = new Book();
        book.setIsbn(bookDTO.getIsbn());
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setAppearanceDate(bookDTO.getAppearanceDate());
        book.setNrOfPages(bookDTO.getNrOfPages());
        book.setCategory(bookDTO.getCategory());
        book.setLanguage(bookDTO.getLanguage());
        return book;
    }

    public static BookDTO book2BookDTOWithoutLibrary(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setIsbn(book.getIsbn());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setAppearanceDate(book.getAppearanceDate());
        bookDTO.setNrOfPages(book.getNrOfPages());
        bookDTO.setCategory(book.getCategory());
        bookDTO.setLanguage(book.getLanguage());
        return bookDTO;
    }
}
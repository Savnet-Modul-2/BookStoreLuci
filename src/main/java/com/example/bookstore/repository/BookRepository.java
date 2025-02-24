package com.example.bookstore.repository;



import com.example.bookstore.entities.Book;
import com.example.bookstore.entities.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByLibrary(Library library);

    List<Book> findByAuthor(String author);

    List<Book> findByTitleIgnoreCase(String title);

    List<Book> findByLanguage(String language);

    List<Book> findByCategory(String category);
}


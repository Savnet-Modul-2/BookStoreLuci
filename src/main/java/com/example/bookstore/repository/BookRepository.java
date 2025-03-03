package com.example.bookstore.repository;

import com.example.bookstore.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findAll(Pageable pageable);

    @Query(value = """
        SELECT book FROM book book
        WHERE (:author IS NULL OR book.author LIKE CONCAT('%', :author, '%'))
        AND (:title IS NULL OR book.title LIKE CONCAT('%', :title, '%'))
    """)
    Page<Book> findBooks(String author, String title, Pageable pageable);
}
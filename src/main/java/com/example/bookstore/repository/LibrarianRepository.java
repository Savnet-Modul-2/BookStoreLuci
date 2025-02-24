package com.example.bookstore.repository;



import com.example.bookstore.entities.Librarian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibrarianRepository extends JpaRepository<Librarian, Long> {

    Librarian findByEmail(String email);

    Librarian findByName(String name);
}

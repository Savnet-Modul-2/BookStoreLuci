package com.example.bookstore.service;

import com.example.bookstore.entities.Book;
import com.example.bookstore.entities.Exemplary;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.ExemplaryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExemplaryService {
    @Autowired
    private ExemplaryRepository exemplaryRepository;

    @Autowired
    private BookRepository bookRepository;

    public List<Exemplary> create(Long bookId, Integer nrExemplaries, Exemplary exemplary) {
        if (exemplary.getId() != null) {
            throw new RuntimeException("You cannot provide an ID to a new exemplary that you want to create");
        }

        Book existentBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Book with id " + bookId + " not found"));

        List<Exemplary> exemplaries = new ArrayList<>();

        for (int i = 0; i < nrExemplaries; ++i) {
            Exemplary newExemplary = new Exemplary();
            newExemplary.setPublisher(exemplary.getPublisher());
            newExemplary.setMaxBorrowDays(exemplary.getMaxBorrowDays());
            newExemplary.setBook(existentBook);

            existentBook.addExemplary(newExemplary);
            exemplaries.add(exemplaryRepository.save(newExemplary));
        }

        return exemplaries;
    }

    public Page<Exemplary> findAllPaginated(Long bookId, Integer pageNumber, Integer numberOfElements) {
        Pageable pageable = (pageNumber != null && numberOfElements != null)
                ? PageRequest.of(pageNumber, numberOfElements)
                : Pageable.unpaged();

        return exemplaryRepository.findByBookId(bookId, pageable);
    }

    public void removeExemplary(Long exemplaryId) {
        if (!exemplaryRepository.existsById(exemplaryId)) {
            throw new EntityNotFoundException("Exemplary with id " + exemplaryId + " not found");
        }
        exemplaryRepository.deleteById(exemplaryId);
    }

    public void removeExemplaryFromBook(Long bookId, Long exemplaryId) {
        Exemplary existentExemplary = exemplaryRepository.findById(exemplaryId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Exemplary with id " + exemplaryId + " not found"));

        Book existentBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Book with id " + bookId + " not found"));

        existentBook.removeExemplary(existentExemplary);
        existentExemplary.setBook(null);
        bookRepository.save(existentBook);
    }
}
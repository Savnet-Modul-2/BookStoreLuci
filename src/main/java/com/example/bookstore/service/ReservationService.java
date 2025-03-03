package com.example.bookstore.service;

import com.example.bookstore.entities.*;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.ExemplaryRepository;
import com.example.bookstore.repository.ReservationRepository;
import com.example.bookstore.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ExemplaryRepository exemplaryRepository;

    public Reservation create(Reservation reservation, Long userId, Long bookId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));

        Exemplary exemplary = exemplaryRepository
                .findFirstAvailableExemplary(bookId, reservation.getStartDate(), reservation.getEndDate())
                .orElseThrow(() -> new IllegalStateException("No available exemplaries in the period."));

        reservation.setReservationStatus(ReservationStatus.PENDING);
        reservation.setUser(user);
        reservation.setExemplary(exemplary);

        return reservationRepository.save(reservation);
    }
}
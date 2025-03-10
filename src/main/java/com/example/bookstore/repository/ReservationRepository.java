package com.example.bookstore.repository;

import com.example.bookstore.entities.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query(value = """
        SELECT reservation FROM reservation reservation
        WHERE reservation.startDate < :today
        AND reservation.reservationStatus = 'PENDING'
    """)
    List<Reservation> findAllReservationsToBeCanceled(LocalDate today);

    @Query(value = """
        SELECT reservation FROM reservation reservation
        WHERE reservation.endDate < :today
        AND reservation.reservationStatus = 'IN_PROGRESS'
    """)
    List<Reservation> findAllReservationsToBeDelayed(LocalDate today);

    @Query(value = """
        SELECT reservation FROM reservation reservation
        WHERE reservation.exemplary.book.library.id = :libraryId
        AND reservation.endDate <= :endDate
        AND reservation.startDate >= :startDate
        ORDER BY reservation.startDate ASC
    """)
    Page<Reservation> findReservationsForLibraryInInterval(Long libraryId,
                                                           LocalDate startDate,
                                                           LocalDate endDate,
                                                           Pageable pageable);

    @Query(value = """
        SELECT reservation FROM reservation reservation
        WHERE reservation.user.id = :userId
        ORDER BY reservation.reservationStatus ASC
    """)
    Page<Reservation> findReservationsForUserByStatus(Long userId, Pageable pageable);
}
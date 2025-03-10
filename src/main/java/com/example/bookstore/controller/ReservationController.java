package com.example.bookstore.controller;

import com.example.bookstore.dto.ReservationDTO;
import com.example.bookstore.dto.validation.ValidationOrder;
import com.example.bookstore.entities.Reservation;
import com.example.bookstore.mapper.ReservationMapper;
import com.example.bookstore.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController()
@RequestMapping("/reservations")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @PostMapping("/{userId}/{bookId}")
    public ResponseEntity<?> create(@PathVariable Long userId,
                                    @PathVariable Long bookId,
                                    @Validated(ValidationOrder.class) @RequestBody ReservationDTO reservationDTO) {
        Reservation reservationToCreate = ReservationMapper.reservationDTO2Reservation(reservationDTO);
        Reservation createdReservation = reservationService.create(reservationToCreate, userId, bookId);
        return ResponseEntity.ok(ReservationMapper.reservation2ReservationDTO(createdReservation));
    }

    @GetMapping("/library/{libraryId}")
    public ResponseEntity<?> getReservationsForLibraryInInterval(@PathVariable(name = "libraryId") Long libraryId,
                                                                 @RequestParam LocalDate startDate,
                                                                 @RequestParam LocalDate endDate,
                                                                 @RequestParam(required = false) Integer page,
                                                                 @RequestParam(required = false) Integer size) {
        Page<Reservation> reservations = reservationService.getReservationsForLibraryInInterval(libraryId,
                startDate, endDate, page, size);
        List<Reservation> reservationList = reservations.getContent();
        return ResponseEntity.ok(ReservationMapper.reservationList2ReservationDTOList(reservationList));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getReservationsForUserByStatus(@PathVariable(name = "userId") Long userId,
                                                            @RequestParam(required = false) Integer page,
                                                            @RequestParam(required = false) Integer size) {
        Page<Reservation> reservations = reservationService.getReservationsForUserByStatus(userId,
                page, size);
        List<Reservation> reservationList = reservations.getContent();
        return ResponseEntity.ok(ReservationMapper.reservationList2ReservationDTOList(reservationList));
    }

    @PutMapping("/{librarianId}/{reservationId}")
    public ResponseEntity<?> changeStatus(@PathVariable Long librarianId,
                                          @PathVariable Long reservationId,
                                          @RequestBody ReservationDTO updatedReservationDTO) {
        Reservation reservationToUpdate = ReservationMapper.reservationDTO2Reservation(updatedReservationDTO);
        Reservation updatedReservation = reservationService.changeStatus(librarianId,
                reservationId, reservationToUpdate);
        return ResponseEntity.ok(ReservationMapper.reservation2ReservationDTO(updatedReservation));
    }
}
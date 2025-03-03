package com.example.bookstore.controller;

import com.example.bookstore.dto.ReservationDTO;
import com.example.bookstore.entities.Reservation;
import com.example.bookstore.mapper.ReservationMapper;
import com.example.bookstore.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/reservations")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @PostMapping("/{userId}/{bookId}")
    public ResponseEntity<?> create(@PathVariable Long userId,
                                    @PathVariable Long bookId,
                                    @RequestBody ReservationDTO reservationDTO) {
        Reservation reservationToCreate = ReservationMapper.reservationDTO2Reservation(reservationDTO);
        Reservation createdReservation = reservationService.create(reservationToCreate, userId, bookId);
        return ResponseEntity.ok(ReservationMapper.reservation2ReservationDTO(createdReservation));
    }
}
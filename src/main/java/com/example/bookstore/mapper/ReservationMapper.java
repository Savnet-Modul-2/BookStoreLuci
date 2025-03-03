package com.example.bookstore.mapper;

import com.example.bookstore.dto.ReservationDTO;
import com.example.bookstore.entities.Reservation;

public class ReservationMapper {
    public static Reservation reservationDTO2Reservation(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();
        reservation.setStartDate(reservationDTO.getStartDate());
        reservation.setEndDate(reservationDTO.getEndDate());
        reservation.setReservationStatus(reservationDTO.getReservationStatus());
        if (reservationDTO.getUser() != null) {
            reservation.setUser(UserMapper.userDTO2User((reservationDTO.getUser())));
        }
        if (reservationDTO.getExemplary() != null) {
            reservation.setExemplary(ExemplaryMapper.exemplaryDTO2Exemplary(reservationDTO.getExemplary()));
        }
        return reservation;
    }

    public static ReservationDTO reservation2ReservationDTO(Reservation reservation) {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setId(reservation.getId());
        reservationDTO.setStartDate(reservation.getStartDate());
        reservationDTO.setEndDate(reservation.getEndDate());
        reservationDTO.setReservationStatus(reservation.getReservationStatus());
        if (reservation.getUser() != null) {
            reservationDTO.setUser(UserMapper.user2UserDTO(reservation.getUser()));
        }
        if (reservation.getExemplary() != null) {
            reservationDTO.setExemplary(ExemplaryMapper.exemplary2ExemplaryDTO(reservation.getExemplary()));
        }
        return reservationDTO;
    }
}
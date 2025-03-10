package com.example.bookstore.dto;

import com.example.bookstore.dto.validation.AdvancedValidation;
import com.example.bookstore.dto.validation.BasicValidation;
import com.example.bookstore.dto.validation.ValidDateOrder;
import com.example.bookstore.dto.validation.DateInTheFuture;
import com.example.bookstore.entities.ReservationStatus;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@ValidDateOrder(groups = AdvancedValidation.class)
public class ReservationDTO {
    private Long id;
    @NotNull(groups = BasicValidation.class)
    @DateInTheFuture(groups = AdvancedValidation.class)
    private LocalDate startDate;
    @NotNull(groups = BasicValidation.class)
    @DateInTheFuture(groups = AdvancedValidation.class)
    private LocalDate endDate;
    private ReservationStatus reservationStatus;
    private UserDTO user;
    private ExemplaryDTO exemplary;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public ExemplaryDTO getExemplary() {
        return exemplary;
    }

    public void setExemplary(ExemplaryDTO exemplary) {
        this.exemplary = exemplary;
    }
}
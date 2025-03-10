package com.example.bookstore.dto.validation;

import com.example.bookstore.dto.ReservationDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DateOrderValidator implements ConstraintValidator<ValidDateOrder, ReservationDTO> {
    @Override
    public void initialize(ValidDateOrder validDate) {
    }

    @Override
    public boolean isValid(ReservationDTO reservationDTO, ConstraintValidatorContext context) {
        return reservationDTO.getStartDate().isBefore(reservationDTO.getEndDate()) ||
                reservationDTO.getStartDate().equals(reservationDTO.getEndDate());
    }
}
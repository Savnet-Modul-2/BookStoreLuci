package com.example.bookstore.dto.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class DateInTheFutureValidator implements ConstraintValidator<DateInTheFuture, LocalDate> {
    @Override
    public void initialize(DateInTheFuture dateInTheFuture) {
    }

    @Override
    public boolean isValid(LocalDate startDate, ConstraintValidatorContext context) {
        return LocalDate.now().isBefore(startDate) ||
                LocalDate.now().equals(startDate);
    }
}

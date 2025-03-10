package com.example.bookstore.dto.validation;

import jakarta.validation.GroupSequence;

@GroupSequence({BasicValidation.class, AdvancedValidation.class})
public interface ValidationOrder {
}
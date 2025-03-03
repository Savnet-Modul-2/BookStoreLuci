package com.example.bookstore.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.InputMismatchException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InputMismatchException.class)
    public ResponseEntity<?> handleInputMismatchException(InputMismatchException inputMismatchException) {
        ErrorDetail error = new ErrorDetail(inputMismatchException.getMessage());
        return new ResponseEntity<>(error, BAD_REQUEST);
        //return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<?> handleIllegalStateException(IllegalStateException illegalStateException) {
        ErrorDetail error = new ErrorDetail(illegalStateException.getMessage());
        return new ResponseEntity<>(error, BAD_REQUEST);
    }
}

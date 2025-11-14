package com.multitech.FileHandler.exceptionHandler;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFound(EntityNotFoundException e, HttpServletRequest req) {
        ErrorResponse er = new ErrorResponse(
                "ENTITY NOT FOUND",
                HttpStatus.NOT_FOUND, //404
                e.getMessage(),
                req.getRequestURI(),
                LocalDateTime.now()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(er);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolation(DataIntegrityViolationException e, HttpServletRequest request) {
        ErrorResponse er = new ErrorResponse(
                "DATA INTEGRITY VIOLATION",
                HttpStatus.BAD_REQUEST, // 400
                e.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(er);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException e, HttpServletRequest request) {
        ErrorResponse er = new ErrorResponse(
                "CONSTRAINT VIOLATION",
                HttpStatus.BAD_REQUEST, // 400
                e.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(er);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception e, HttpServletRequest request) {
        ErrorResponse er = new ErrorResponse(
                "INTERNAL SERVER ERROR",
                HttpStatus.INTERNAL_SERVER_ERROR, // 500
                e.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(er);
    }
}

package com.multitech.FileHandler.exceptionHandler;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorResponse(
        String title,
        HttpStatus status,
        String detail,
        String instance,
        LocalDateTime timestamp
) {
}
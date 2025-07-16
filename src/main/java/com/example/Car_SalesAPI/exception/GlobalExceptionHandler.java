package com.example.Car_SalesAPI.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Map<Class<? extends Exception>, HttpStatus> EXCEPTION_STATUS_MAP = Map.of(
            UserNotFoundException.class, HttpStatus.NOT_FOUND,
            AnnouncementNotFoundException.class, HttpStatus.NOT_FOUND,
            HelpNotFoundException.class, HttpStatus.NOT_FOUND,
            RolesNotFoundException.class, HttpStatus.NOT_FOUND,
            InvalidEmailOrPasswordException.class, HttpStatus.BAD_REQUEST,
            EmailIsAllReadyTakenException.class, HttpStatus.BAD_REQUEST,
            BookmarksNotFoundException.class, HttpStatus.NOT_FOUND,
            CardsNotFoundException.class, HttpStatus.NOT_FOUND
    );

    @ExceptionHandler({
            UserNotFoundException.class,
            AnnouncementNotFoundException.class,
            InvalidEmailOrPasswordException.class,
            HelpNotFoundException.class,
            RolesNotFoundException.class,
            EmailIsAllReadyTakenException.class,
            BookmarksNotFoundException.class,
            CardsNotFoundException.class
    })

    public ResponseEntity<Map<String, Object>> handleCustomExceptions(Exception e) {
        HttpStatus status = EXCEPTION_STATUS_MAP.getOrDefault(e.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);

        Map<String, Object> body = new HashMap<>();
        body.put("status", status.value());
        body.put("message", e.getMessage());
        body.put("timestamp", LocalDateTime.now());

        return new ResponseEntity<>(body, status);
    }
}

package com.example.Car_SalesAPI.exception;

public class EmailIsAllReadyTakenException extends RuntimeException {
    public EmailIsAllReadyTakenException(String message) {
        super(message);
    }
}

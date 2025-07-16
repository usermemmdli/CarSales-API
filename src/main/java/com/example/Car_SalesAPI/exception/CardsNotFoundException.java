package com.example.Car_SalesAPI.exception;

public class CardsNotFoundException extends RuntimeException {
    public CardsNotFoundException(String message) {
        super(message);
    }
}

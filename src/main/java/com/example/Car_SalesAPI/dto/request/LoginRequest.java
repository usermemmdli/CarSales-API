package com.example.Car_SalesAPI.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequest {
    @NotNull(message = "Phone number cannot be null")
    private String phoneNumber;
    @NotNull(message = "Password cannot be null")
    private String password;
}
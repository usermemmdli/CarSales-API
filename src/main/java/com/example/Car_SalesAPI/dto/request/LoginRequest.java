package com.example.Car_SalesAPI.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequest {
    @NotNull
    private String phoneNumber;
    @NotNull
    private String password;
}
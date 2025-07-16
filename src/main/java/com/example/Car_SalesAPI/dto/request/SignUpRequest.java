package com.example.Car_SalesAPI.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SignUpRequest {
    @NotNull(message = "Name cannot be null")
    private String name;
    private String surname;
    @NotNull(message = "Phone number cannot be null")
    private String phoneNumber;
    @NotNull(message = "Password number cannot be null")
    private String password;
}
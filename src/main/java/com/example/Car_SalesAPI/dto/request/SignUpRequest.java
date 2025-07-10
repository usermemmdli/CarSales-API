package com.example.Car_SalesAPI.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SignUpRequest {
    @NotNull
    private String name;
    private String surname;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String password;
}
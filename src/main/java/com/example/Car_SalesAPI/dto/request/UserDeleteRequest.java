package com.example.Car_SalesAPI.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDeleteRequest {
    @NotBlank(message = "This field cannot be blank")
    String password;
}

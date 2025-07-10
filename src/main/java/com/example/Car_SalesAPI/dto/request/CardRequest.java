package com.example.Car_SalesAPI.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CardRequest {
    private String userId;
    @Size(max = 16, min = 16)
    private String cardNumber;
    @Size(max = 3)
    private String CVV;
    private String expiredDate;
}

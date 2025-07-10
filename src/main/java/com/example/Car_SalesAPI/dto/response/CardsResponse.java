package com.example.Car_SalesAPI.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardsResponse {
    String id;
    String cardNumber;
}

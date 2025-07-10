package com.example.Car_SalesAPI.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserProfileResponse {
    private String name;
    private String surname;
    private String phoneNumber;
}

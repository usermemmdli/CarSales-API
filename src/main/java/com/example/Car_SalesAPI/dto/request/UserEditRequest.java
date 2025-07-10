package com.example.Car_SalesAPI.dto.request;

import lombok.Data;

@Data
public class UserEditRequest {
    private String name;
    private String surname;
    private String phoneNumber;
}

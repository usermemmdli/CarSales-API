package com.example.Car_SalesAPI.dto.request;

import lombok.Data;

@Data
public class UserChangePasswordRequest {
    String oldPassword;
    String newPassword;
}

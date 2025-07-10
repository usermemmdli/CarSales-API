package com.example.Car_SalesAPI.mapper;

import com.example.Car_SalesAPI.dao.entity.Users;
import com.example.Car_SalesAPI.dto.response.UserProfileResponse;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public class UsersMapper {
    public UserProfileResponse toUsersProfileResponse(Users user) {
        return UserProfileResponse.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }
}

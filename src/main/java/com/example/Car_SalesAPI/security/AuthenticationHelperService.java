package com.example.Car_SalesAPI.security;

import com.example.Car_SalesAPI.dao.entity.Users;
import com.example.Car_SalesAPI.dao.repository.UsersRepository;
import com.example.Car_SalesAPI.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationHelperService {
    private final UsersRepository usersRepository;

    public Users getAuthenticatedUser(String currentPhoneNumber) {
        return usersRepository.findByPhoneNumber(currentPhoneNumber)
                .orElseThrow(() -> new UserNotFoundException("User not found with phone number: " + currentPhoneNumber));
    }

    public String getCurrentPhoneNumber() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}

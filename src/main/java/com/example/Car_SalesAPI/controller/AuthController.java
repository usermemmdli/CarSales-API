package com.example.Car_SalesAPI.controller;

import com.example.Car_SalesAPI.dto.request.LoginRequest;
import com.example.Car_SalesAPI.dto.request.SignUpRequest;
import com.example.Car_SalesAPI.dto.response.JwtResponse;
import com.example.Car_SalesAPI.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public void signUpUser(@RequestBody @Valid SignUpRequest signUpRequest) {
        authService.signUpUser(signUpRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> loginUser(@RequestBody @Valid LoginRequest loginRequest) {
        JwtResponse jwtResponse = authService.loginUser(loginRequest);
        return ResponseEntity.ok(jwtResponse);
    }
}

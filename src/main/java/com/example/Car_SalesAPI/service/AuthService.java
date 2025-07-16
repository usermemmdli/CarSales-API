package com.example.Car_SalesAPI.service;

import com.example.Car_SalesAPI.dao.entity.Roles;
import com.example.Car_SalesAPI.dao.entity.Users;
import com.example.Car_SalesAPI.dao.repository.RolesRepository;
import com.example.Car_SalesAPI.dao.repository.UsersRepository;
import com.example.Car_SalesAPI.dto.request.LoginRequest;
import com.example.Car_SalesAPI.dto.request.SignUpRequest;
import com.example.Car_SalesAPI.dto.response.JwtResponse;
import com.example.Car_SalesAPI.exception.EmailIsAllReadyTakenException;
import com.example.Car_SalesAPI.exception.InvalidEmailOrPasswordException;
import com.example.Car_SalesAPI.exception.RolesNotFoundException;
import com.example.Car_SalesAPI.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UsersRepository usersRepository;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public void signUpUser(SignUpRequest signUpRequest) {
        if (usersRepository.existsByPhoneNumber((signUpRequest.getPhoneNumber()))) {
            throw new EmailIsAllReadyTakenException("Email is already taken");
        }
        Users users = new Users();

        Roles defaultRole = rolesRepository.findByName("USER")
                .orElseThrow(() -> new RolesNotFoundException("No roles found"));

        users.setRoles(defaultRole);
        users.setName(signUpRequest.getName());
        users.setSurname(signUpRequest.getSurname());
        users.setPhoneNumber(signUpRequest.getPhoneNumber());
        users.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        users.setCreatedAt(Timestamp.from(Instant.now()));
        usersRepository.save(users);
    }

    public JwtResponse loginUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getPhoneNumber(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Users users = usersRepository.findByPhoneNumber(loginRequest.getPhoneNumber())
                .orElseThrow(() -> new InvalidEmailOrPasswordException("Email or password is invalid"));
        String token = jwtService.createToken(users);

        return new JwtResponse(token);
    }
}

package com.example.Car_SalesAPI.service;

import com.example.Car_SalesAPI.dao.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;

}

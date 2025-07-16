package com.example.Car_SalesAPI.security;

import com.example.Car_SalesAPI.dao.entity.Users;
import com.example.Car_SalesAPI.dao.repository.UsersRepository;
import com.example.Car_SalesAPI.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUsersDetailsService implements UserDetailsService {
    private final UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        Users users = usersRepository.findByPhoneNumber((phoneNumber))
                .orElseThrow(() -> new UserNotFoundException("User not found with phone number: " + phoneNumber));

        return org.springframework.security.core.userdetails.User
                .withUsername(users.getPhoneNumber())
                .password(users.getPassword())
                .authorities("ROLE_" + users.getRoles().getName())
                .build();
    }
}

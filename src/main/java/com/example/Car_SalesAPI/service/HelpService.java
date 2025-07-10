package com.example.Car_SalesAPI.service;

import com.example.Car_SalesAPI.dao.entity.Help;
import com.example.Car_SalesAPI.dao.entity.Users;
import com.example.Car_SalesAPI.dto.request.HelpRequest;
import com.example.Car_SalesAPI.security.AuthenticationHelperService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class HelpService {
    private final AuthenticationHelperService authenticationHelperService;

    public void sendHelpRequest(String currentPhoneNumber, HelpRequest helpRequest) {
        Users user = authenticationHelperService.getAuthenticatedUser(currentPhoneNumber);
        Help help = new Help();
        help.setUserId(user.getId());
        help.setDescription(helpRequest.getDescription());
        help.setStatus(true);
        help.setCreatedAt(Date.from(Instant.now()));
    }
}

package com.example.Car_SalesAPI.controller;

import com.example.Car_SalesAPI.dto.request.HelpRequest;
import com.example.Car_SalesAPI.security.AuthenticationHelperService;
import com.example.Car_SalesAPI.service.HelpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/help")
@RequiredArgsConstructor
public class HelpController {
    private final HelpService helpService;
    private final AuthenticationHelperService authenticationHelperService;

    @PostMapping
    @PreAuthorize("hasAnyRole('USER','SELLER')")
    @ResponseStatus(HttpStatus.CREATED)
    public void sendHelpRequest(@RequestBody HelpRequest helpRequest) {
        String currentPhoneNumber = authenticationHelperService.getCurrentPhoneNumber();
        helpService.sendHelpRequest(currentPhoneNumber, helpRequest);
    }
}

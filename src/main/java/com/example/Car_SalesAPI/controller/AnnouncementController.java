package com.example.Car_SalesAPI.controller;

import com.example.Car_SalesAPI.dto.request.AnnouncementRequest;
import com.example.Car_SalesAPI.dto.response.AnnouncementResponse;
import com.example.Car_SalesAPI.security.AuthenticationHelperService;
import com.example.Car_SalesAPI.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user-ads")
@RequiredArgsConstructor
public class AnnouncementController {
    private final AuthenticationHelperService authenticationHelperService;
    private final AnnouncementService announcementService;

    @GetMapping
    @PreAuthorize("hasAnyRole('USER','SELLER')")
    public ResponseEntity<List<AnnouncementResponse>> getAllUserAnnouncements() {
        String currentPhoneNumber = authenticationHelperService.getCurrentPhoneNumber();
        List<AnnouncementResponse> announcementResponse = announcementService.getAllUserAnnouncement(currentPhoneNumber);
        return ResponseEntity.ok(announcementResponse);
    }

    @GetMapping("/published")
    @PreAuthorize("hasAnyRole('USER','SELLER')")
    public ResponseEntity<List<AnnouncementResponse>> getPublishedAnnouncements() {
        String currentPhoneNumber = authenticationHelperService.getCurrentPhoneNumber();
        List<AnnouncementResponse> announcementResponse = announcementService.getPublishedAnnouncements(currentPhoneNumber);
        return ResponseEntity.ok(announcementResponse);
    }

    @GetMapping("/expired")
    @PreAuthorize("hasAnyRole('USER','SELLER')")
    public ResponseEntity<List<AnnouncementResponse>> getExpiredAnnouncements() {
        String currentPhoneNumber = authenticationHelperService.getCurrentPhoneNumber();
        List<AnnouncementResponse> announcementResponse = announcementService.getExpiredAnnouncements(currentPhoneNumber);
        return ResponseEntity.ok(announcementResponse);
    }

    @GetMapping("/rejected")
    @PreAuthorize("hasAnyRole('USER','SELLER')")
    public ResponseEntity<List<AnnouncementResponse>> getRejectedAnnouncements() {
        String currentPhoneNumber = authenticationHelperService.getCurrentPhoneNumber();
        List<AnnouncementResponse> announcementResponse = announcementService.getRejectedAnnouncements(currentPhoneNumber);
        return ResponseEntity.ok(announcementResponse);
    }

    @PostMapping("/new")
    @PreAuthorize("hasAnyRole('USER','SELLER')")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewAnnouncement(@RequestBody AnnouncementRequest announcementRequest) {
        String currentPhoneNumber = authenticationHelperService.getCurrentPhoneNumber();
        announcementService.addNewAnnouncement(currentPhoneNumber, announcementRequest);
    }
}

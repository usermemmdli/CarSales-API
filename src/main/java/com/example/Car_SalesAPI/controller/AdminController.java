package com.example.Car_SalesAPI.controller;

import com.example.Car_SalesAPI.dto.request.CheckAdsRequest;
import com.example.Car_SalesAPI.dto.request.HelpStatusRequest;
import com.example.Car_SalesAPI.dto.response.pagination.AnnouncementPageResponse;
import com.example.Car_SalesAPI.dto.response.pagination.HelpPageResponse;
import com.example.Car_SalesAPI.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/announcement")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AnnouncementPageResponse> getAnnouncement(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                    @RequestParam(value = "count", defaultValue = "5") int count){
        AnnouncementPageResponse announcementPageResponse = adminService.getAnnouncement(page, count);
        return ResponseEntity.ok(announcementPageResponse);
    }

    @PatchMapping("/announcement")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public void checkAds(@RequestBody CheckAdsRequest checkAdsRequest) {
        adminService.checkAds(checkAdsRequest);
    }

    @GetMapping("/help-user")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HelpPageResponse> getHelpUser(@RequestParam(value = "page", defaultValue = "0") int page,
                                                        @RequestParam(value = "count", defaultValue = "5") int count) {
        HelpPageResponse helpPageResponse = adminService.getHelpUser(page, count);
        return ResponseEntity.ok(helpPageResponse);
    }

    @PatchMapping("/help-user")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public void helpUser(@RequestBody HelpStatusRequest helpStatusRequest) {
        adminService.helpUser(helpStatusRequest);
    }
}

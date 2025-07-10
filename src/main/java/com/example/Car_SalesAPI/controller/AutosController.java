package com.example.Car_SalesAPI.controller;

import com.example.Car_SalesAPI.dto.response.pagination.AnnouncementPageResponse;
import com.example.Car_SalesAPI.security.AuthenticationHelperService;
import com.example.Car_SalesAPI.service.AutosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/autos")
@RequiredArgsConstructor
public class AutosController {
    private final AutosService autosService;
    private final AuthenticationHelperService authenticationHelperService;

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'SELLER')")
    public ResponseEntity<AnnouncementPageResponse> getAnnouncement(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                    @RequestParam(value = "count", defaultValue = "20") int count) {
        AnnouncementPageResponse announcementPageResponse = autosService.getAnnouncement(page, count);
        return ResponseEntity.ok(announcementPageResponse);
    }

    @PostMapping("/bookmarks")
    @PreAuthorize("hasAnyRole('USER', 'SELLER')")
    public void addCarInBookmarks(@RequestParam String id) {
        String currentPhoneNumber = authenticationHelperService.getCurrentPhoneNumber();
        autosService.addCarInBookmarks(currentPhoneNumber, id);
    }
}

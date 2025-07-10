package com.example.Car_SalesAPI.controller;

import com.example.Car_SalesAPI.dto.response.pagination.AnnouncementPageResponse;
import com.example.Car_SalesAPI.security.AuthenticationHelperService;
import com.example.Car_SalesAPI.service.BookmarksService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bookmarks")
@RequiredArgsConstructor
public class BookmarksController {
    private final BookmarksService bookmarksService;
    private final AuthenticationHelperService authenticationHelperService;

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'SELLER')")
    public ResponseEntity<AnnouncementPageResponse> getMarkedAnnouncements(@RequestParam(value = "page", defaultValue = "0") int page,
                                                                           @RequestParam(value = "count", defaultValue = "20") int count) {
        String currentPhoneNumber = authenticationHelperService.getCurrentPhoneNumber();
        AnnouncementPageResponse announcementPageResponse = bookmarksService.getMarkedAnnouncement(currentPhoneNumber, page, count);
        return ResponseEntity.ok(announcementPageResponse);
    }

    @DeleteMapping
    @PreAuthorize("hasAnyRole('USER', 'SELLER')")
    public void removeMarkedAnnouncement(@RequestParam String id) {
        bookmarksService.removeMarkedAnnouncement(id);
    }
}

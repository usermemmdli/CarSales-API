package com.example.Car_SalesAPI.service;

import com.example.Car_SalesAPI.dao.entity.Announcement;
import com.example.Car_SalesAPI.dao.entity.Bookmarks;
import com.example.Car_SalesAPI.dao.entity.Users;
import com.example.Car_SalesAPI.dao.repository.AnnouncementRepository;
import com.example.Car_SalesAPI.dao.repository.BookmarksRepository;
import com.example.Car_SalesAPI.dto.response.AnnouncementResponse;
import com.example.Car_SalesAPI.dto.response.pagination.AnnouncementPageResponse;
import com.example.Car_SalesAPI.exception.BookmarksNotFoundException;
import com.example.Car_SalesAPI.mapper.AnnouncementMapper;
import com.example.Car_SalesAPI.security.AuthenticationHelperService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookmarksService {
    private final BookmarksRepository bookmarksRepository;
    private final AnnouncementRepository announcementRepository;
    private final AnnouncementMapper announcementMapper;
    private final AuthenticationHelperService authenticationHelperService;

    public AnnouncementPageResponse getMarkedAnnouncement(String currentPhoneNumber, int page, int count) {
        Users user = authenticationHelperService.getAuthenticatedUser(currentPhoneNumber);
        Pageable pageable = PageRequest.of(page, count);
        Bookmarks userBookmarks = bookmarksRepository.findByUsersId(user.getId())
                .orElseThrow(() -> new BookmarksNotFoundException("Bookmarks not found"));

        Page<Announcement> allAnnouncements = announcementRepository.findAllById(userBookmarks.getAnnouncementId(), pageable);

        List<AnnouncementResponse> allAnnouncementsList = allAnnouncements.getContent().stream()
                .map(announcementMapper::toUserAnnouncementResponse)
                .toList();

        return new AnnouncementPageResponse(
                allAnnouncementsList,
                allAnnouncements.getTotalElements(),
                allAnnouncements.getTotalPages(),
                allAnnouncements.hasNext()
        );
    }

    public void removeMarkedAnnouncement(String id) {
        if (bookmarksRepository.existsById(id)) {
            bookmarksRepository.deleteById(id);
        } else {
            throw new BookmarksNotFoundException("Bookmarks not found");
        }
    }
}

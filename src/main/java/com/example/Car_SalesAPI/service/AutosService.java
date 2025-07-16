package com.example.Car_SalesAPI.service;

import com.example.Car_SalesAPI.dao.entity.Announcement;
import com.example.Car_SalesAPI.dao.entity.Bookmarks;
import com.example.Car_SalesAPI.dao.entity.Users;
import com.example.Car_SalesAPI.dao.repository.AnnouncementRepository;
import com.example.Car_SalesAPI.dao.repository.BookmarksRepository;
import com.example.Car_SalesAPI.dto.response.AnnouncementResponse;
import com.example.Car_SalesAPI.dto.response.pagination.AnnouncementPageResponse;
import com.example.Car_SalesAPI.exception.AnnouncementNotFoundException;
import com.example.Car_SalesAPI.exception.BookmarksNotFoundException;
import com.example.Car_SalesAPI.mapper.AnnouncementMapper;
import com.example.Car_SalesAPI.security.AuthenticationHelperService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AutosService {
    private final AnnouncementRepository announcementRepository;
    private final BookmarksRepository bookmarksRepository;
    private final AnnouncementMapper announcementMapper;
    private final AuthenticationHelperService authenticationHelperService;

    public AnnouncementPageResponse getAnnouncement(int page, int count) {
        Pageable pageable = PageRequest.of(page, count);
        Page<Announcement> allAnnouncements = announcementRepository.findAll(pageable);
        List<AnnouncementResponse> allAnnouncementsList = allAnnouncements.getContent().stream()
                .filter(announcement -> announcement.getIsActive() == true)
                .map(announcementMapper::toUserAnnouncementResponse)
                .toList();

        return new AnnouncementPageResponse(
                allAnnouncementsList,
                allAnnouncements.getTotalElements(),
                allAnnouncements.getTotalPages(),
                allAnnouncements.hasNext()
        );
    }

    public void addCarInBookmarks(String currentPhoneNumber, String id) {
        Users user = authenticationHelperService.getAuthenticatedUser(currentPhoneNumber);
        Announcement announcement = announcementRepository.findById(id)
                .orElseThrow(() -> new AnnouncementNotFoundException("Announcement not found"));

        if (!bookmarksRepository.existsByUsersId(user.getId())) {
            Bookmarks bookmarks = new Bookmarks();
            bookmarks.setUsersId(user.getId());
            if (bookmarks.getAnnouncementId() == null) {
                bookmarks.setAnnouncementId(new ArrayList<>());
            }
            bookmarks.getAnnouncementId().add(announcement.getId());
            bookmarks.setCreatedAt(Date.from(Instant.now()));
            bookmarksRepository.save(bookmarks);
        } else {
            Bookmarks bookmarks = bookmarksRepository.findByUsersId(user.getId())
                    .orElseThrow(() -> new BookmarksNotFoundException("Bookmarks not found"));
            bookmarks.getAnnouncementId().add(announcement.getId());
            bookmarksRepository.save(bookmarks);
        }
    }
}

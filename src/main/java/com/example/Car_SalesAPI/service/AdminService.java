package com.example.Car_SalesAPI.service;

import com.example.Car_SalesAPI.dao.entity.Announcement;
import com.example.Car_SalesAPI.dao.entity.Help;
import com.example.Car_SalesAPI.dao.repository.AnnouncementRepository;
import com.example.Car_SalesAPI.dao.repository.HelpRepository;
import com.example.Car_SalesAPI.dto.request.CheckAdsRequest;
import com.example.Car_SalesAPI.dto.request.HelpStatusRequest;
import com.example.Car_SalesAPI.dto.response.AnnouncementResponse;
import com.example.Car_SalesAPI.dto.response.HelpResponse;
import com.example.Car_SalesAPI.dto.response.pagination.AnnouncementPageResponse;
import com.example.Car_SalesAPI.dto.response.pagination.HelpPageResponse;
import com.example.Car_SalesAPI.exception.AnnouncementNotFoundException;
import com.example.Car_SalesAPI.exception.HelpNotFoundException;
import com.example.Car_SalesAPI.mapper.AnnouncementMapper;
import com.example.Car_SalesAPI.mapper.HelpMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AnnouncementRepository announcementRepository;
    private final HelpRepository helpRepository;
    private final HelpMapper helpMapper;
    private final AnnouncementMapper announcementMapper;

    public AnnouncementPageResponse getAnnouncement(int page, int count) {
        Pageable pageable = PageRequest.of(page, count);
        Page<Announcement> allAnnouncements = announcementRepository.findAll(pageable);
        List<AnnouncementResponse> allAnnouncementsList = allAnnouncements.getContent().stream()
                .map(announcementMapper::toUserAnnouncementResponse)
                .collect(Collectors.toList());

        return new AnnouncementPageResponse(
                allAnnouncementsList,
                allAnnouncements.getTotalElements(),
                allAnnouncements.getTotalPages(),
                allAnnouncements.hasNext()
        );
    }

    public void checkAds(CheckAdsRequest checkAdsRequest) {
        Announcement announcement = announcementRepository.findById(checkAdsRequest.getId())
                .orElseThrow(() -> new AnnouncementNotFoundException("Announcement not found"));
        announcement.setIsActive(checkAdsRequest.getIsActive());
        announcement.setUpdatedAt(Date.from(Instant.now()));
        announcementRepository.save(announcement);
    }

    public HelpPageResponse getHelpUser(int page, int count) {
        Pageable pageable = PageRequest.of(page, count);
        Page<Help> allHelps = helpRepository.findAll(pageable);
        List<HelpResponse> allHelpsList = allHelps.getContent().stream()
                .map(helpMapper::toHelpResponse)
                .collect(Collectors.toList());

        return new HelpPageResponse(
                allHelpsList,
                allHelps.getTotalElements(),
                allHelps.getTotalPages(),
                allHelps.hasNext()
        );
    }

    public void helpUser(HelpStatusRequest helpStatusRequest) {
        Help help = helpRepository.findById(helpStatusRequest.getId())
                .orElseThrow(() -> new HelpNotFoundException("Help not found"));
        help.setStatus(helpStatusRequest.getStatus());
        help.setUpdatedAt(Date.from(Instant.now()));
        helpRepository.save(help);
    }
}

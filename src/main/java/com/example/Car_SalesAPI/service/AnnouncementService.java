package com.example.Car_SalesAPI.service;

import com.example.Car_SalesAPI.dao.entity.Announcement;
import com.example.Car_SalesAPI.dao.entity.Roles;
import com.example.Car_SalesAPI.dao.entity.Users;
import com.example.Car_SalesAPI.dao.repository.AnnouncementRepository;
import com.example.Car_SalesAPI.dao.repository.RolesRepository;
import com.example.Car_SalesAPI.dao.repository.UsersRepository;
import com.example.Car_SalesAPI.dto.request.AnnouncementRequest;
import com.example.Car_SalesAPI.dto.response.AnnouncementResponse;
import com.example.Car_SalesAPI.exception.RolesNotFoundException;
import com.example.Car_SalesAPI.mapper.AnnouncementMapper;
import com.example.Car_SalesAPI.security.AuthenticationHelperService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnnouncementService {
    private final AnnouncementRepository announcementRepository;
    private final AnnouncementMapper announcementMapper;
    private final AuthenticationHelperService authenticationHelperService;
    private final UsersRepository usersRepository;
    private final RolesRepository rolesRepository;

    public List<AnnouncementResponse> getAllUserAnnouncement(String currentPhoneNumber) {
        Users user = authenticationHelperService.getAuthenticatedUser(currentPhoneNumber);
        List<Announcement> announcement = announcementRepository.findAllByUserId(user.getId());
        return announcement.stream()
                .map(announcementMapper::toUserAnnouncementResponse)
                .collect(Collectors.toList());
    }

    public List<AnnouncementResponse> getPublishedAnnouncements(String currentPhoneNumber) {
        Users user = authenticationHelperService.getAuthenticatedUser(currentPhoneNumber);
        List<Announcement> announcement = announcementRepository.findAllByUserId(user.getId());
        return announcement.stream()
                .filter(getPublished -> getPublished.getIsActive() == true && Date.from(Instant.now()).before(getPublished.getExpireDate()))
                .map(announcementMapper::toUserAnnouncementResponse)
                .collect(Collectors.toList());
    }

    public List<AnnouncementResponse> getExpiredAnnouncements(String currentPhoneNumber) {
        Users user = authenticationHelperService.getAuthenticatedUser(currentPhoneNumber);
        List<Announcement> announcement = announcementRepository.findAllByUserId(user.getId());
        return announcement.stream()
                .filter(getExpired -> getExpired.getExpireDate().before(Date.from(Instant.now())))
                .map(announcementMapper::toUserAnnouncementResponse)
                .collect(Collectors.toList());
    }

    public List<AnnouncementResponse> getRejectedAnnouncements(String currentPhoneNumber) {
        Users user = authenticationHelperService.getAuthenticatedUser(currentPhoneNumber);
        List<Announcement> announcement = announcementRepository.findAllByUserId(user.getId());
        return announcement.stream()
                .filter(getRejected -> getRejected.getIsActive() == false)
                .map(announcementMapper::toUserAnnouncementResponse)
                .collect(Collectors.toList());
    }

    public void addNewAnnouncement(String currentPhoneNumber, AnnouncementRequest announcementRequest) {
        Users user = authenticationHelperService.getAuthenticatedUser(currentPhoneNumber);
        Announcement announcement = new Announcement();
        announcement.setCity(announcementRequest.getCity());
        announcement.setBrand(announcementRequest.getBrand());
        announcement.setModel(announcementRequest.getModel());
        announcement.setReleaseYear(announcementRequest.getReleaseYear());
        announcement.setTypeOfRoof(announcementRequest.getTypeOfRoof());
        announcement.setColor(announcementRequest.getColor());
        announcement.setMarching(announcementRequest.getMarching());
        announcement.setGearbox(announcementRequest.getGearbox());
        announcement.setIsNew(announcementRequest.getIsNew());
        announcement.setStatus(announcementRequest.getStatus());
        announcement.setProductionCountry(announcementRequest.getProductionCountry());
        announcement.setDescription(announcementRequest.getDescription());
        announcement.setUserId(user.getId());
        announcement.setExpireDate(DateUtils.addMonths(Date.from(Instant.now()), 1));
        announcement.setIsActive(true);
        announcement.setCreatedAt(Date.from(Instant.now()));
        announcement.setUpdatedAt(Date.from(Instant.now()));
        if (!announcementRepository.existsByUserId(user.getId())) {
            Roles sellerRoles = rolesRepository.findByName("SELLER")
                    .orElseThrow(() -> new RolesNotFoundException("Roles not found"));
            user.setRoles(sellerRoles);
        }
        announcementRepository.save(announcement);
        usersRepository.save(user);
    }
}

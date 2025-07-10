package com.example.Car_SalesAPI.mapper;

import com.example.Car_SalesAPI.dao.entity.Announcement;
import com.example.Car_SalesAPI.dto.response.AnnouncementResponse;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public class AnnouncementMapper {
    public AnnouncementResponse toUserAnnouncementResponse(Announcement announcement) {
        return AnnouncementResponse.builder()
                .id(announcement.getId())
                .city(announcement.getCity())
                .brand(announcement.getBrand())
                .model(announcement.getModel())
                .releaseYear(announcement.getReleaseYear())
                .typeOfRoof(announcement.getTypeOfRoof())
                .color(announcement.getColor())
                .marching(announcement.getMarching())
                .gearbox(announcement.getGearbox())
                .isNew(announcement.getIsNew())
                .status(announcement.getStatus())
                .productionCountry(announcement.getProductionCountry())
                .description(announcement.getDescription())
                .expireDate(announcement.getExpireDate())
                .updatedAt(announcement.getUpdatedAt())
                .build();
    }
}

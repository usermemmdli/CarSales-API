package com.example.Car_SalesAPI.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class AnnouncementResponse {
    private String id;
    private String city;
    private String brand;
    private String model;
    private String releaseYear;
    private String typeOfRoof;
    private String color;
    private String marching;
    private String gearbox;
    private Boolean isNew;
    private String status;
    private String productionCountry;
    private String description;
    private Date expireDate;
    private Date updatedAt;
}

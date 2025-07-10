package com.example.Car_SalesAPI.dto.request;

import lombok.Data;

@Data
public class AnnouncementRequest {
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
    private Long userId;
}

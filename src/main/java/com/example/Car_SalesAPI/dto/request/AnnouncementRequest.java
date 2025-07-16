package com.example.Car_SalesAPI.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AnnouncementRequest {
    private String city;
    @NotBlank(message = "This field cannot be blank")
    private String brand;
    @NotBlank(message = "This field cannot be blank")
    private String model;
    @NotBlank(message = "This field cannot be blank")
    private String releaseYear;
    private String typeOfRoof;
    private String color;
    private String marching;
    private String gearbox;
    @NotBlank(message = "This field cannot be blank")
    private Boolean isNew;
    @NotBlank(message = "This field cannot be blank")
    private String status;
    private String productionCountry;
    @NotBlank(message = "This field cannot be blank")
    private String description;
    private Long userId;
}

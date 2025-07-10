package com.example.Car_SalesAPI.dao.entity;

import jakarta.persistence.Id;
import jdk.jfr.Enabled;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "announcement")
@Enabled
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Announcement {
    @Id
    String id;
    String city;
    String brand;
    String model;
    String releaseYear;
    String typeOfRoof;
    String color;
    String marching;
    String gearbox;
    Boolean isNew;
    String status;
    String productionCountry;
    String description;
    Long userId;
    Date expireDate;
    Boolean isActive;
    Date createdAt;
    Date updatedAt;
}

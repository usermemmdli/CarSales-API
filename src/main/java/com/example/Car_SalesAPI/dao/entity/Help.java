package com.example.Car_SalesAPI.dao.entity;

import jdk.jfr.Enabled;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "help")
@Enabled
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Help {
    String id;
    Long userId;
    String description;
    Boolean status;
    Date createdAt;
    Date updatedAt;
}

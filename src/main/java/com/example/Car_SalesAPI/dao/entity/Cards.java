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

@Document(collection = "cards")
@Enabled
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Cards {
    @Id
    String id;
    Long userId;
    String cardNumber;
    String CVV;
    String expiredDate;
    Date createdAt;
}

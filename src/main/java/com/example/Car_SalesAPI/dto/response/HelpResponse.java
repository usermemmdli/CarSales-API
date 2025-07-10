package com.example.Car_SalesAPI.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class HelpResponse {
    private Long userId;
    private String description;
    private Boolean status;
    private Date createdAt;
    private Date updatedAt;
}

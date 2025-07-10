package com.example.Car_SalesAPI.dto.response.pagination;

import com.example.Car_SalesAPI.dto.response.AnnouncementResponse;

import java.util.List;

public record AnnouncementPageResponse
        (List<AnnouncementResponse> productsResponse,
         long totalElements,
         int totalPages,
         boolean hasNextPages) {
}

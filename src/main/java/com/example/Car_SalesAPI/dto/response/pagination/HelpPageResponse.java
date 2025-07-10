package com.example.Car_SalesAPI.dto.response.pagination;

import com.example.Car_SalesAPI.dto.response.HelpResponse;

import java.util.List;

public record HelpPageResponse
        (List<HelpResponse> productsResponse,
         long totalElements,
         int totalPages,
         boolean hasNextPages) {
}

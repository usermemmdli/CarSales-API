package com.example.Car_SalesAPI.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountResponse {
    Integer countAnnouncements;
    Integer countRejectedAnnouncements;
    Integer countExpiredAnnouncements;
}

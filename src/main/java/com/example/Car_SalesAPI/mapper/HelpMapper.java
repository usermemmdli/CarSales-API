package com.example.Car_SalesAPI.mapper;

import com.example.Car_SalesAPI.dao.entity.Help;
import com.example.Car_SalesAPI.dto.response.HelpResponse;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public class HelpMapper {
    public HelpResponse toHelpResponse(Help help){
        return HelpResponse.builder()
                .userId(help.getUserId())
                .description(help.getDescription())
                .status(help.getStatus())
                .createdAt(help.getCreatedAt())
                .updatedAt(help.getUpdatedAt())
                .build();
    }
}

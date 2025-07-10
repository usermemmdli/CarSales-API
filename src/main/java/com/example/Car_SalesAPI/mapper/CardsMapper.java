package com.example.Car_SalesAPI.mapper;

import com.example.Car_SalesAPI.dao.entity.Cards;
import com.example.Car_SalesAPI.dto.response.CardsResponse;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public class CardsMapper {
    public CardsResponse toCardResponse(Cards card) {
        return CardsResponse.builder()
                .id(card.getId())
                .cardNumber(card.getCardNumber())
                .build();
    }
}

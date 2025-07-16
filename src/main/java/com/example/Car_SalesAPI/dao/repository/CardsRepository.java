package com.example.Car_SalesAPI.dao.repository;

import com.example.Car_SalesAPI.dao.entity.Cards;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardsRepository extends MongoRepository<Cards, String> {
    List<Cards> findAllByIdIn(List<String> ids);

    void deleteByUserId(Long userId);

    boolean existsByUserId(Long userId);
}

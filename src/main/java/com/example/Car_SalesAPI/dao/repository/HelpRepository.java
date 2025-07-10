package com.example.Car_SalesAPI.dao.repository;

import com.example.Car_SalesAPI.dao.entity.Help;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HelpRepository extends MongoRepository<Help, String> {
}

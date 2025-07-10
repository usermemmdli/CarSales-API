package com.example.Car_SalesAPI.dao.repository;

import com.example.Car_SalesAPI.dao.entity.Bookmarks;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BookmarksRepository extends MongoRepository<Bookmarks, String> {
    boolean existsByUsersId(Long usersId);

    Optional<Bookmarks> findByUsersId(Long usersId);
}

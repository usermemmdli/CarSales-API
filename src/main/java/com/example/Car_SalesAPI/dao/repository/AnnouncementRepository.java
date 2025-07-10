package com.example.Car_SalesAPI.dao.repository;

import com.example.Car_SalesAPI.dao.entity.Announcement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnnouncementRepository extends MongoRepository<Announcement, String> {
    @Query("{ '_id': { $in: ?0 } }")
    Page<Announcement> findAllById(List<String> id, Pageable pageable);

    void deleteByUserId(Long userId);

    List<Announcement> findAllByUserId(Long userId);

    boolean existsByUserId(Long userId);
}

package com.example.Car_SalesAPI.dao.repository;

import com.example.Car_SalesAPI.dao.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {
    Optional<Roles> findByName(String user);
}

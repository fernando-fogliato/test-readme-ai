package com.example.springbootcrudapp.repository;

import com.example.springbootcrudapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find user by email
    Optional<User> findByEmail(String email);

    // Find users by name containing (case-insensitive)
    List<User> findByNameContainingIgnoreCase(String name);

    // Custom query to find users by phone
    @Query("SELECT u FROM User u WHERE u.phone = :phone")
    List<User> findByPhone(@Param("phone") String phone);

    // Check if user exists by email
    boolean existsByEmail(String email);
} 
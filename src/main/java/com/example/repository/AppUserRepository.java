package com.example.repository;


import com.example.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    // Custom method to find a user by username
    Optional<AppUser> findByUsername(String username);
}

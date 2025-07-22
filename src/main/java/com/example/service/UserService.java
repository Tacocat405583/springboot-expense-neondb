package com.example.service;

import com.example.model.AppUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    AppUser saveUser(AppUser user);
    AppUser findUserByUsername(String username);
    Optional<AppUser> findUserById(Long id); // New method to find user by ID


}

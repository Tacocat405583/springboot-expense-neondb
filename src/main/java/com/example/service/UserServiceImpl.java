package com.example.service;

import com.example.model.AppUser;
import com.example.repository.AppUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final AppUserRepository appUserRepository;

    public UserServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public AppUser saveUser(AppUser user) {
        return appUserRepository.save(user);
    }

    @Override
    public AppUser findUserByUsername(String username) {
        return appUserRepository.findByUsername(username).orElse(null);
    }

    @Override
    public Optional<AppUser> findUserById(Long id) {
        return appUserRepository.findById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // If AppUser implements UserDetails, you can return it directly.
        // Otherwise, convert it to a UserDetails object:
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities("USER") // Replace with actual roles/authorities
                .build();
    }
}
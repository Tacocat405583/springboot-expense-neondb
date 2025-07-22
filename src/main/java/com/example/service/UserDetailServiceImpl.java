package com.example.service;

import com.example.model.AppUser;
import com.example.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
@Primary
public class UserDetailServiceImpl implements UserService {

    @Autowired
    private AppUserRepository appUserRepository;


    public UserDetailServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AppUser appUser = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        //creates an authority for the user based on their role
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + appUser.getRole().name());

        return new User(appUser.getUsername(), appUser.getPassword(), Collections.singleton(authority));//assigns the authority to the user
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
}

package com.example.config;

import com.example.repository.AppUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class DataInitializer implements CommandLineRunner {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        if(appUserRepository.findByUsername("admin").isEmpty()) {
            // Create an admin user if it doesn't exist
            com.example.model.AppUser adminUser = new com.example.model.AppUser();
            adminUser.setUsername("admin");
            adminUser.setPassword(passwordEncoder.encode("admin123"));
            adminUser.setFullName("Admin User");
            adminUser.setRole(com.example.model.Role.ADMIN); // Assuming Role is an enum with ADMIN
            System.out.println("Creating admin user with username: " + adminUser.getUsername() + " and password: " + adminUser.getPassword());
            appUserRepository.save(adminUser);
        }

    }
}

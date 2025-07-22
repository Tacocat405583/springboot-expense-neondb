package com.example.config;


import com.example.security.JwtAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{// Injecting AuthenticationConfiguration to get the AuthenticationManager
        return authenticationConfiguration.getAuthenticationManager();// This bean is used to manage authentication
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Using BCryptPasswordEncoder for password hashing
        return new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF protection as we are using tokens
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()))  // Allow frames from only the same origin
                .authorizeHttpRequests(authz ->
                        authz
                                .requestMatchers("/signup").permitAll()  // Allow unauthenticated access to signUp
                                .requestMatchers("/login").permitAll()  // Allow public access to login
                                .requestMatchers("/h2-console/**").permitAll() // Allow public access to h2-console for now
                                .requestMatchers("/admin/**").hasRole("ADMIN") // Only ADMIN role can access /admin/** endpoints
                                .anyRequest().hasRole("USER")
                        // All other requests require authentication
                )
        .addFilterBefore(jwtAuthFilter, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class); // Add JWT filter before UsernamePasswordAuthenticationFilter;
        return http.build();
    }



}

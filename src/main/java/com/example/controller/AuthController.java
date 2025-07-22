package com.example.controller;


import com.example.dto.AppUserDTO;
import com.example.dto.AuthDTO;
import com.example.dto.AuthResponseDto;
import com.example.model.AppUser;
import com.example.service.AuthService;
import com.example.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;


@RestController
public class AuthController {


    private final AuthService authService;

    public AuthController(AuthService authService) {

        this.authService = authService;
    }




    @PostMapping("/signup")
    public ResponseEntity<AuthResponseDto> signup(@RequestBody AppUserDTO appUserDTO){
        AuthResponseDto responseDto = authService.register(appUserDTO);// Register the user and get the response DTO. mcuh faster
        if ("success".equals(responseDto.getMessage())) {
            return ResponseEntity.ok(responseDto); // Return bad request if registration fails
        }else {
            return ResponseEntity.badRequest().body(responseDto); // Return bad request if registration fails
        }

    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody AuthDTO authDTO){


        AuthResponseDto responseDto = authService.loginUser(authDTO); // Login the user and get the response DTO
        if ("success".equals(responseDto.getMessage())) {
            return ResponseEntity.ok(responseDto); // Return OK if login is successful
        } else {
            return ResponseEntity.badRequest().body(responseDto); // Return bad request if login fails
        }
    }


}

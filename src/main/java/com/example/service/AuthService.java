package com.example.service;

import com.example.dto.AppUserDTO;
import com.example.dto.AuthDTO;
import com.example.dto.AuthResponseDto;

public interface AuthService {
    AuthResponseDto register(AppUserDTO appUserDTO);
    AuthResponseDto loginUser(AuthDTO authDTO); //we dont need the additional full name field here


}

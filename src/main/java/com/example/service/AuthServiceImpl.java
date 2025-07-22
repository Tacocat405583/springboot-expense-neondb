package com.example.service;

import com.example.dto.AppUserDTO;
import com.example.dto.AuthDTO;
import com.example.dto.AuthResponseDto;
import com.example.model.AppUser;
import com.example.model.Role;
import com.example.utils.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private  final JwtUtils jwtUtil;

    public AuthServiceImpl(UserService userService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtils jwtUtil) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public AuthResponseDto register(AppUserDTO appUserDTO) {

        if(userService.findUserByUsername(appUserDTO.getUsername())!=null){
            //return new AuthResponseDto(null, "User name already exists");
            return new AuthResponseDto(null,"error","User name already exists");
        }
        AppUser appUser = new AppUser();
        appUser.setFullName(appUserDTO.getFullName());
        appUser.setUsername(appUserDTO.getUsername());
        appUser.setPassword(passwordEncoder.encode(appUserDTO.getPassword()));
        appUser.setRole(Role.USER);

        userService.saveUser(appUser);//save user and find user by method will return the saved user

        AuthDTO authDTO = new AuthDTO();
        authDTO.setUsername(appUserDTO.getUsername());
        authDTO.setPassword(appUserDTO.getPassword());

        return loginUser(authDTO);

    }

    @Override
    public AuthResponseDto loginUser(AuthDTO authDTO) {
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authDTO.getUsername(), authDTO.getPassword())
            );
        }catch (BadCredentialsException e){
            return new AuthResponseDto(null,"error","Invalid username or password");
        }
        final String token = jwtUtil.generateToken(authDTO.getUsername());

        return new AuthResponseDto(token, "success", "Login successful");
    }
}

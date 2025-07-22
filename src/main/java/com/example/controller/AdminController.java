package com.example.controller;


import com.example.model.AppUser;
import com.example.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/admin")
public class AdminController {//all endpoints related to admin functionalities will be defined here
    // For example, you can add methods to manage users, view reports, etc.

    // Example method to get all users (this would typically call a service)
    // @GetMapping("/users")
    // public ResponseEntity<List<AppUser>> getAllUsers() {
    //     List<AppUser> users = adminService.getAllUsers();
    //     return ResponseEntity.ok(users);
    // }


    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<AppUser>> getAllUsers() {
        List<AppUser> users = adminService.getAllUsers();
        return ResponseEntity.ok(users);
    }



}

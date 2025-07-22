package com.example.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "app_user")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    @Column(unique = true)
    private String username;
    private String password;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)//many expenses can be associated with one user
    private List<Expense> expenses;// List of expenses associated with the user

    @Enumerated(EnumType.STRING)//user, admin in string format
    private Role role;

}

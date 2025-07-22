package com.example.dto;


import lombok.Data;

@Data
public class AppUserDTO {
    private Long id;
    private String fullName; // lowercase 'f'
    private String username;
    private String password;

    public AppUserDTO() {
    }

    public AppUserDTO(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}


//@Data
//public class AppUserDTO {
//    private Long id;
//    private String fullName; // lowercase 'f'
//    private String username;
//    private String password;
//
//    public AppUserDTO() {
//    }
//
//    public AppUserDTO(Long id, String username, String password) {
//        this.id = id;
//        this.username = username;
//        this.password = password;
//    }
//}
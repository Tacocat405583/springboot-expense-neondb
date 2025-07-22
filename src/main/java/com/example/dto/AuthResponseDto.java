package com.example.dto;


import lombok.Data;

@Data
public class AuthResponseDto {

    private String token;
    private String message;

    //these are the constructor and getters/setters
    public AuthResponseDto() {
    }
    public AuthResponseDto(String token, String message) {
        this.token = token;
        this.message = message;
    }

    public AuthResponseDto(Object o, String error, String userNameAlreadyExists) {
        // This constructor is not used in the current context, but it can be used to create an instance with specific parameters.
        this.token = (String) o; // Assuming 'o' is a token string
        this.message = error; // Assuming 'error' is a message string
        // The 'userNameAlreadyExists' parameter is not used in this constructor, but it can be utilized if needed.

    }
}

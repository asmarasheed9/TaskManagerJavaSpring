package com.example.javatraining.dto;

public class AuthResponse {
    private String message;
    private String token;

    public AuthResponse(String loginSuccessful, String token) {
        this.message = loginSuccessful;
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }
}

package com.example.demo.DTO.AcccountDTO;


import jakarta.validation.constraints.NotNull;

public class ReFreshTokenRequest {
    @NotNull(message = "Token must not be null")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

package com.example.demo.DTO.AcccountDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class LoginRequestDTO {
    @NotNull(message = "Email must be not null")
    @NotBlank(message = "Email must be not blank")
    private String email;
    @NotNull(message = "Password must be not null")
    @NotBlank(message = "Password must be not blank")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}

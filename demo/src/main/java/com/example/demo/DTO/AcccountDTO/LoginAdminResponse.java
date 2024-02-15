package com.example.demo.DTO.AcccountDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginAdminResponse {
    private int id;
    private String name;
    private String email;
    private String token;
}

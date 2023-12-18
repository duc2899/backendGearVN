package com.example.demo.DTO.AcccountDTO;

import com.example.demo.modal.UserModalPackage.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponseDTO {
    private int id;
    private String name;
    private Role role;
    private String email;
    private String createdAt;
    private String phoneNumber;
}

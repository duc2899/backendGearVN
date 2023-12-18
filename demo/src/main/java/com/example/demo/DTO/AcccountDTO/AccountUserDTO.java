package com.example.demo.DTO.AcccountDTO;

import com.example.demo.modal.UserModalPackage.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountUserDTO {
    private String name;
    private int id;
    private Role role;
    private String email;
    private String phoneNumber;
    private String createdAt;
    private String password;
    private Boolean isActive;
}

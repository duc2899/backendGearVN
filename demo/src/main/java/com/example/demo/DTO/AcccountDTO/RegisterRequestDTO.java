package com.example.demo.DTO.AcccountDTO;

import com.example.demo.modal.AddressNotePackage.LoginType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequestDTO {
    private String name;
    private String password;
    private String email;
    private String phoneNumber;
    private LoginType type;
}

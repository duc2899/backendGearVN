package com.example.demo.DTO.AcccountDTO;

import com.example.demo.DTO.AcccountDTO.CartDTO.CartResponseDTO;
import com.example.demo.modal.AddressNotePackage.LoginType;
import com.example.demo.modal.UserModalPackage.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponseDTO {
    private String accessToken;
    private String userName;
    private int id;
    private Role role;
    private String email;
    private String phoneNumber;
    private List<CartResponseDTO> cart;
    private LoginType type;
}

package com.example.demo.DTO.AcccountDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChangeInforUserResponseDTO {
    private int id;
    private String email;
    private String phoneNumber;
    private String userName;
}
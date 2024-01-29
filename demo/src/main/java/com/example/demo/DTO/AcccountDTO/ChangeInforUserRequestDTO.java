package com.example.demo.DTO.AcccountDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChangeInforUserRequestDTO {
    private Integer id;
    private String email;
    private String phoneNumber;
    private String userName;
    private String password;
    private Boolean isActive;
}

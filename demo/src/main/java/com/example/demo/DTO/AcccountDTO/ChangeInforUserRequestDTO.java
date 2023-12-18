package com.example.demo.DTO.AcccountDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private String name;
    private String password;
    private Boolean isActive;
}

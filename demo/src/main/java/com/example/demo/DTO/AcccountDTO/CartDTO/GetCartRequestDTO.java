package com.example.demo.DTO.AcccountDTO.CartDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetCartRequestDTO {
    private int idUser;
}

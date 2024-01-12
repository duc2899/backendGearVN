package com.example.demo.DTO.DiscountCodeDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetListDCResponseDTO {
    private int id;
    private int condition_price;
    private int reduce_price;
    private LocalDateTime expiry;
    private String code;
}

package com.example.demo.DTO.AcccountDTO.CartDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartResponseDTO {
    private int id;
    private String title;
    private Double oldPrice;
    private Double saleRate;
    private String image;
    private String nameCategory;
    private int amount;
    private int idProduct;
}

package com.example.demo.DTO.ProductDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindProductResponseDTO {
    private int id;
    private double oldPrice;
    private double saleRate;
    private String image;
    private String title;
    private String category;
}

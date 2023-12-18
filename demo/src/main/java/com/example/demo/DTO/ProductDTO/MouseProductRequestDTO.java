package com.example.demo.DTO.ProductDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MouseProductRequestDTO {
    private String title;
    private Double oldPrice;
    private Double saleRate;
    private Integer quantity;
    private String image;
    private int id_category;
    private int description;
    private LaptopPropertiesDTO laptopProperties;
}

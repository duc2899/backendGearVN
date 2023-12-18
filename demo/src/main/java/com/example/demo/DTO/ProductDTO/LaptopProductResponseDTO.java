package com.example.demo.DTO.ProductDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LaptopProductResponseDTO {
    private int id;
    private String title;
    private Double oldPrice;
    private Double saleRate;
    private int quantity;
    private String image;
    private String description;
    private String name_category;
}

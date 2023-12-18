package com.example.demo.DTO.ProductDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EditLaptopProductRequestDTO {
    private String title;
    private Double oldPrice;
    private Double saleRate;
    private int quantity;
    private String image;
    private int id_category;
    private LaptopPropertiesDTO laptopProperties;

}

package com.example.demo.DTO.ProductDTO.ProductLaptopDTO;

import com.example.demo.DTO.ProductDTO.ProductLaptopDTO.LaptopPropertiesDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LaptopProductRequestDTO {
    private String title;
    private Double oldPrice;
    private Double saleRate;
    private Integer quantity;
    private String image;
    private int idCategory;
    private String description;
    private int idProducer;
    private LaptopPropertiesDTO laptopProperties;
}

package com.example.demo.DTO.ProductDTO.ProductLaptopDTO;

import com.example.demo.DTO.ProductDTO.ProductRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LaptopProductRequestDTO extends ProductRequestDTO {
    private LaptopPropertiesDTO properties;
}

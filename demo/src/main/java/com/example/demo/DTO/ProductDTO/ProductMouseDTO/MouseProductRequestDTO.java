package com.example.demo.DTO.ProductDTO.ProductMouseDTO;

import com.example.demo.DTO.ProductDTO.ProductRequestDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MouseProductRequestDTO extends ProductRequestDTO {
   @Valid
   private MousePropertiesDTO properties;
}

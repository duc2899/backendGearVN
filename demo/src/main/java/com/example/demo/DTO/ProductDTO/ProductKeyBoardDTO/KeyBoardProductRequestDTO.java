package com.example.demo.DTO.ProductDTO.ProductKeyBoardDTO;

import com.example.demo.DTO.ProductDTO.ProductRequestDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeyBoardProductRequestDTO extends ProductRequestDTO {
    @Valid
    private KeyBoardPropertiesDTO properties;
}

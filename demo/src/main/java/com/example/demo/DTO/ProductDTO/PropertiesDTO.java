package com.example.demo.DTO.ProductDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PropertiesDTO {
    private int id;
    private String properties;
    private String name;
    private Boolean isPublic;
}

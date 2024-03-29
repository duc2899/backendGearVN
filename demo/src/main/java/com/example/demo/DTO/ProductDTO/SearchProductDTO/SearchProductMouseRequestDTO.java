package com.example.demo.DTO.ProductDTO.SearchProductDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchProductMouseRequestDTO {
    private List<String> dpi;
    private List<String> size;
    private List<String> connection;
    private List<String> charger;
    private List<String> rgb;
    private List<String> color;
}

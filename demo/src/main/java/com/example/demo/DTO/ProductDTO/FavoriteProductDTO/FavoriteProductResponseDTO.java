package com.example.demo.DTO.ProductDTO.FavoriteProductDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FavoriteProductResponseDTO {
    private String title;
    private int id;
    private int idProduct;
    private String image;
    private Double oldPrice;
    private Double saleRate;
    private String type;
}

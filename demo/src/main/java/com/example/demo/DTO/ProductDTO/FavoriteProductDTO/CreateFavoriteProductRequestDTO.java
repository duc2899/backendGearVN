package com.example.demo.DTO.ProductDTO.FavoriteProductDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateFavoriteProductRequestDTO {
    private int idUser;
    private int idProduct;
}

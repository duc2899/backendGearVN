package com.example.demo.DTO.ProductDTO.FeedbackDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FeedbackRequestDTO {
    private int idProduct;
    private int idUser;
    private String message;
    private int star;

}

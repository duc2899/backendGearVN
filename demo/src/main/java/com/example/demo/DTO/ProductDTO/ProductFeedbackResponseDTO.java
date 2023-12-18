package com.example.demo.DTO.ProductDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductFeedbackResponseDTO {
    private int id;
    private String createdDate;
    private String message;
    private int star;
    private String username;
}

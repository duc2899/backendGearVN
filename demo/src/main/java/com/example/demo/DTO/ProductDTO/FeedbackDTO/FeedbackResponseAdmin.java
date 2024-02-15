package com.example.demo.DTO.ProductDTO.FeedbackDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FeedbackResponseAdmin {
    private int id;
    private int idProduct;
    private String product;
    private String user;
    private String image;
    private String message;
    private int star;
    private String createdDate;
}

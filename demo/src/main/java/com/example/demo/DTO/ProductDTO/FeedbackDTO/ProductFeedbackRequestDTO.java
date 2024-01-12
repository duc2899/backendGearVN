package com.example.demo.DTO.ProductDTO.FeedbackDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductFeedbackRequestDTO {
  private int idUser;
  private int idLaptop;
  private int star;
  private String message;
}

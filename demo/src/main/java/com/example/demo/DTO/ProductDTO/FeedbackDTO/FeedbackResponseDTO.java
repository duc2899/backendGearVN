package com.example.demo.DTO.ProductDTO.FeedbackDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FeedbackResponseDTO {
    private int id;
    private String name;
    private String message;
    private int star;
    private String createdDate;
    private Boolean isEdit;

}

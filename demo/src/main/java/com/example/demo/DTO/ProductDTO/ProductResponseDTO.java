package com.example.demo.DTO.ProductDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDTO {
    private int id;
    private String title;
    private Double oldPrice;
    private Double saleRate;
    private Integer quantity;
    private String image;
    private String description;
    private String type;
    private String producer;
    private List<PropertiesDTO> properties;
    private List<FeedbackProductResponseDTO> dataFeedback;
    private List<PreviewImageResponseDTO> previewImages;
}

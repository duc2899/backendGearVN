package com.example.demo.DTO.ProductDTO.ProductLaptopDTO;

import com.example.demo.DTO.ProductDTO.FeedbackProductResponseDTO;
import com.example.demo.DTO.ProductDTO.PreviewImageResponseDTO;
import com.example.demo.DTO.ProductDTO.PropertiesDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LaptopProductDTO {
    private int id;
    private String title;
    private Double oldPrice;
    private Double saleRate;
    private int quantity;
    private String image;
    private String description;
    private String type;
    private List<PropertiesDTO> properties;
    private List<FeedbackProductResponseDTO> dataFeedback;
    private List<PreviewImageResponseDTO> previewImages;
}

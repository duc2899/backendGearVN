package com.example.demo.DTO.ProductDTO.ProductLaptopDTO;

import com.example.demo.DTO.ProductDTO.FeedbackProductResponseDTO;
import com.example.demo.DTO.ProductDTO.PreviewImageResponseDTO;
import com.example.demo.DTO.ProductDTO.PropertiesDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LaptopProductResponseDTO {
    private List<LaptopProductDTO> data;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}

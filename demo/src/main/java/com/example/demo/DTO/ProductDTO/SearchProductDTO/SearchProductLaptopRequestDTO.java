package com.example.demo.DTO.ProductDTO.SearchProductDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchProductLaptopRequestDTO {
    private List<String> cpu;
    private List<String> vga;
    private List<String> ram;
    private List<String> ssd;
    private List<String> screen;
    private List<String> size;
    private List<String> color;
    private List<String> operatingSystem;
}

package com.example.demo.service.CategoryServices;

import com.example.demo.DTO.CategoryDTO.CategoryResponseDTO;
import com.example.demo.modal.CategoryPackage.CategoryModal;
import com.example.demo.repository.ProductRepository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServices {
    private final CategoryRepository categoryRepository;

    public List<CategoryResponseDTO> getAllCategory() {
        List<CategoryModal> categoryModalList = categoryRepository.findAll();
        List<CategoryResponseDTO> categoryResponseDTOS = new ArrayList<>();
        for (CategoryModal item : categoryModalList) {
            CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
            categoryResponseDTO.setId(item.getId_category());
            categoryResponseDTO.setName(item.getName_category());
            categoryResponseDTOS.add(categoryResponseDTO);
        }
        return categoryResponseDTOS;
    }
}

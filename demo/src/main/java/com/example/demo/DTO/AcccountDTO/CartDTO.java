package com.example.demo.DTO.AcccountDTO;

import com.example.demo.modal.ProductModalPackage.ProductModal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDTO {
    private int amount;
    private ProductModal productModal;

}

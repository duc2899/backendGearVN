package com.example.demo.DTO.ProductDTO;

import com.example.demo.modal.ProductModalPackage.ProductType;
import jakarta.validation.constraints.NotNull;

public class DeleteProductRequestDTO {
    @NotNull(message = "id must be not null")
    private int id;
    @NotNull(message = "type must be not null")
    private ProductType type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }
}

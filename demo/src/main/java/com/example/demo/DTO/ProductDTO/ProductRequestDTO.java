package com.example.demo.DTO.ProductDTO;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductRequestDTO {
    @NotNull(message = "Title must be not null")
    @NotBlank(message = "Title must be not blank")
    private String title;
    @NotNull(message = "OldPrice must be not null")
    @Min(value = 1, message = "OldPrice must be greater than zero")
    private Double oldPrice;
    @NotNull(message = "saleRate must be not null")
    @Max(value = 1, message = "saleRate must be lesser than one")
    @Min(value = 0, message = "saleRate must be bigger than zero")
    private Double saleRate;
    @NotNull(message = "quantity must be not null")
    @Min(value = 1, message = "quantity must be greater than zero")
    private Integer quantity;
    private String image;
    @NotNull(message = "idCategory must be not null")
    private int idCategory;
    @NotNull(message = "description must be not null")
    @NotBlank(message = "description must be not blank")
    private String description;
    @NotNull(message = "idProducer must be not null")
    private int idProducer;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(Double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public Double getSaleRate() {
        return saleRate;
    }

    public void setSaleRate(Double saleRate) {
        this.saleRate = saleRate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdProducer() {
        return idProducer;
    }

    public void setIdProducer(int idProducer) {
        this.idProducer = idProducer;
    }
}

package com.example.demo.DTO.AcccountDTO.CartDTO;

import jakarta.validation.constraints.NotNull;

public class CartRequestDTO {

    private int idUser;
    @NotNull(message = "amount must be not null")
    private int amount;
    @NotNull(message = "id_product must be not null")
    private int id_product;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }
}

package com.example.demo.DTO.BillDTO;

import jakarta.validation.constraints.NotNull;


public class GetBillsByUserRequestDTO {
    @NotNull(message = "idUser must be not null")
    private int idUser;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}

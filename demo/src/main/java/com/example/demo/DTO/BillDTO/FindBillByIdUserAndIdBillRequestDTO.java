package com.example.demo.DTO.BillDTO;

import jakarta.validation.constraints.NotNull;


public class FindBillByIdUserAndIdBillRequestDTO {
    @NotNull(message = "idUser must be not null")
    private int idUser;
    @NotNull(message = "idBill must be not null")
    private int idBill;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdBill() {
        return idBill;
    }

    public void setIdBill(int idBill) {
        this.idBill = idBill;
    }

}

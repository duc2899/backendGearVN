package com.example.demo.DTO.BillDTO;

import jakarta.validation.constraints.NotNull;


public class CancelBillRequestDTO {
    @NotNull(message = "idBill must be not null")
    private int idBill;

    public int getIdBill() {
        return idBill;
    }

    public void setIdBill(int idBill) {
        this.idBill = idBill;
    }
}

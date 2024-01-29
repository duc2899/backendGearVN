package com.example.demo.DTO.BillDTO;

import jakarta.validation.constraints.NotNull;


public class EditStatusBillRequestDTO {
    @NotNull(message = "idBill must be not null")
    private int idBill;
    @NotNull(message = "statusBill must be not null")
    private int statusBill;

    public int getIdBill() {
        return idBill;
    }

    public void setIdBill(int idBill) {
        this.idBill = idBill;
    }

    public int getStatusBill() {
        return statusBill;
    }

    public void setStatusBill(int statusBill) {
        this.statusBill = statusBill;
    }
}

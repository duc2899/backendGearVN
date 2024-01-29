package com.example.demo.DTO.BillDTO;

import jakarta.validation.constraints.NotNull;


public class EditPaymentBillRequestDTO {
    @NotNull(message = "idBill must be not null")
    private int idBill;
    @NotNull(message = "isPay must be not null")
    private boolean isPay;

    public int getIdBill() {
        return idBill;
    }

    public void setIdBill(int idBill) {
        this.idBill = idBill;
    }

    public boolean isPay() {
        return isPay;
    }

    public void setPay(boolean pay) {
        isPay = pay;
    }
}

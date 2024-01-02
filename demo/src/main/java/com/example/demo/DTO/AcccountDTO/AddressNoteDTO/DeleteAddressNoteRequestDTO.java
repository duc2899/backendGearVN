package com.example.demo.DTO.AcccountDTO.AddressNoteDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class DeleteAddressNoteRequestDTO {
    @NotNull
    private int idAddress;
    @NotNull
    private int idUser;

    public int getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(int idAddress) {
        this.idAddress = idAddress;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}

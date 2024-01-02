package com.example.demo.DTO.AcccountDTO.AddressNoteDTO;

import jakarta.validation.constraints.NotNull;

public class AddressNoteRequestDTO {
    @NotNull
    private int idUser;
    @NotNull
    private String nameCustomer;
    @NotNull
    private String homeAddress;
    @NotNull
    private String phoneNumber;
    @NotNull
    private WardRequestDTO ward;
    @NotNull
    private DistrictRequestDTO district;
    @NotNull
    private CityRequestDTO city;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public WardRequestDTO getWard() {
        return ward;
    }

    public void setWard(WardRequestDTO ward) {
        this.ward = ward;
    }

    public DistrictRequestDTO getDistrict() {
        return district;
    }

    public void setDistrict(DistrictRequestDTO district) {
        this.district = district;
    }

    public CityRequestDTO getCity() {
        return city;
    }

    public void setCity(CityRequestDTO city) {
        this.city = city;
    }
}


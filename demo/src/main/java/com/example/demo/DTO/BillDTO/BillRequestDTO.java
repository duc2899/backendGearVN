package com.example.demo.DTO.BillDTO;

import com.example.demo.DTO.AcccountDTO.CartDTO.CartRequestDTO;
import com.example.demo.modal.BillModalPackage.PaymentType;
import com.example.demo.modal.BillModalPackage.SexType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;


public class BillRequestDTO {
    @NotNull(message = "sex must not be null")
    private SexType sex;
    @NotNull(message = "address must not be null")
    @NotBlank(message = "address must not be blank")
    private String address;
    @NotNull(message = "isPay must not be null")
    private boolean isPay;
    @NotNull(message = "name must not be null")
    @NotBlank(message = "name must not be blank")
    private String name;
    @NotNull(message = "paymentType must not be null")
    private PaymentType paymentType;
    @NotNull(message = "phoneNumber must not be null")
    @NotBlank(message = "phoneNumber must not be blank")
    private String phoneNumber;
    @NotNull(message = "priceDelivery must not be null")
    private int priceDelivery;
    @NotNull(message = "idUser must not be null")
    private int idUser;

    private String note;
    private String discountCode;

    @NotNull(message = "products must not be null")
    @NotEmpty(message = "products must be not blank")
    @Size(message = "products must not be null")
    private List<CartRequestDTO> products;

    public SexType getSex() {
        return sex;
    }

    public void setSex(SexType sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isPay() {
        return isPay;
    }

    public void setPay(boolean pay) {
        isPay = pay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getPriceDelivery() {
        return priceDelivery;
    }

    public void setPriceDelivery(int priceDelivery) {
        this.priceDelivery = priceDelivery;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public List<CartRequestDTO> getProducts() {
        return products;
    }

    public void setProducts(List<CartRequestDTO> products) {
        this.products = products;
    }
}

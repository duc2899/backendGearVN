package com.example.demo.DTO.BillDTO;

import com.example.demo.modal.BillModalPackage.PaymentType;
import com.example.demo.modal.BillModalPackage.SexType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillResponseDTO {
    private int id;
    private SexType sex;
    private String address;
    private String createdDate;
    private Boolean isPay;
    private String name;
    private String email;
    private PaymentType paymentType;
    private String phoneNumber;
    private double priceDelivery;
    private double totalPrice;
    private double temporaryPrice;
    private double discountPrice;
    private Boolean isCancelOrder;
    private String note;
    private int statusBill;
    private List<ItemResponseBillDTO> products;
}

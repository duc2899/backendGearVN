package com.example.demo.DTO.BillDTO;

import com.example.demo.DTO.AcccountDTO.CartDTO.CartRequestDTO;
import com.example.demo.modal.BillModalPackage.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BillRequestDTO {
    private String address;
    private Boolean isPay;
    private String name;
    private PaymentType paymentType;
    private String phoneNumber;
    private int priceDelivery;
    private int idUser;
    private String discountCode;
    private List<CartRequestDTO> products;
}

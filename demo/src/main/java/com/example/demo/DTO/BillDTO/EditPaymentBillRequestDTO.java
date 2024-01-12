package com.example.demo.DTO.BillDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EditPaymentBillRequestDTO {
    private int idBill;
    private boolean isPay;
}

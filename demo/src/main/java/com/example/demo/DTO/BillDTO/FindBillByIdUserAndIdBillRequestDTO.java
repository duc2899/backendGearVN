package com.example.demo.DTO.BillDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindBillByIdUserAndIdBillRequestDTO {
    private int idUser;
    private int idBill;
}

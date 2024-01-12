package com.example.demo.DTO.BillDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemResponseBillDTO {
    private int id;
    private double price;
    private String name;
    private String image;
    private double saleRate;
    private int amount;
}

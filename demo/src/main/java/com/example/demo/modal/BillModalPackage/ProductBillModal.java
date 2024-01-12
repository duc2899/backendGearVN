package com.example.demo.modal.BillModalPackage;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "product_bill", schema = "gearvn")
public class ProductBillModal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProductBill;

    @Column(name = "title")
    private String title;

    @Column(name = "image")
    private String image;

    @Column(name = "oldPrice")
    private int oldPrice;

    @Column(name = "saleRate")
    private double saleRate;

}

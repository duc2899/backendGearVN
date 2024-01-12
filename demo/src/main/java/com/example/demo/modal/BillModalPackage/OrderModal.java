package com.example.demo.modal.BillModalPackage;

import com.example.demo.modal.ProductModalPackage.ProductModal;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders", schema = "gearvn")
public class OrderModal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_order;

    @Column(name = "amount")
    private int amount;

    @ManyToMany(mappedBy = "orderMeal", cascade = CascadeType.ALL)
    private Set<ProductModal> productModals = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "idBill")
    private BillModal orderBill;


}

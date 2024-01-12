package com.example.demo.modal.CartPackage;

import com.example.demo.modal.ProductModalPackage.ProductModal;
import com.example.demo.modal.UserModalPackage.UserModal;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cart_user", schema = "gearvn")
public class CartModal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_cart;

    @Column(name = "amount")
    private int amount;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private ProductModal productModal;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserModal userCartModal;


}

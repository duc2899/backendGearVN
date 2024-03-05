package com.example.demo.modal.ProductModalPackage;

import com.example.demo.modal.UserModalPackage.UserModal;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "favoriteProduct", schema = "gearvn")
public class FavoriteProductModal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_favorite_product;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_product")
    private ProductModal productModal;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_user")
    private UserModal userModal;

}

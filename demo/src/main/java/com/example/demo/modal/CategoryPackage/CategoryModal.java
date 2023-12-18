package com.example.demo.modal.CategoryPackage;

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
@Table(name = "category", schema = "gearvn")
public class CategoryModal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_category;
    @Column(name = "name_category")
    private String name_category;

    @OneToMany(mappedBy = "categoryModal", fetch = FetchType.LAZY, cascade =  CascadeType.REMOVE)
    private Set<ProductModal> productModals = new HashSet<>();

}

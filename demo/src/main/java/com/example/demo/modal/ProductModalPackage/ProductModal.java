package com.example.demo.modal.ProductModalPackage;

import com.example.demo.modal.CartPackage.CartModal;
import com.example.demo.modal.CategoryPackage.CategoryModal;
import com.example.demo.modal.FeedbackPackage.FeedbackModal;
import com.example.demo.modal.ProducerPackage.ProducerModal;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "product", schema = "gearvn")
public class ProductModal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_product;
    @Column(name = "title")
    private String title;
    @Column(name = "oldPrice")
    private Double oldPrice;
    @Column(name = "saleRate", columnDefinition = "double default 1")
    private Double saleRate;
    @Column(name = "quantity", columnDefinition = "integer default 1")
    private int quantity;
    @Column(name = "image")
    private String image;
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "productModal", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Set<FeedbackModal> productFeedbacks = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_category")
    private CategoryModal categoryModal;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_producer")
    private ProducerModal producerModal;


    @OneToOne(mappedBy = "productMouse")
    private MouseProperties mouseProperties;

    @OneToOne(mappedBy = "productLaptop", orphanRemoval = true, cascade = CascadeType.ALL)
    private LaptopProperties laptopProperties;

    @OneToMany(mappedBy = "productModal")
    private List<CartModal> cartModals;
}

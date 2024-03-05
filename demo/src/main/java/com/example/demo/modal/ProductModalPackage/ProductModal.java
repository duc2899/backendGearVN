package com.example.demo.modal.ProductModalPackage;

import com.example.demo.modal.BillModalPackage.OrderModal;
import com.example.demo.modal.CartPackage.CartModal;
import com.example.demo.modal.CategoryPackage.CategoryModal;
import com.example.demo.modal.FeedbackPackage.FeedbackModal;
import com.example.demo.modal.ProducerPackage.ProducerModal;
import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @Column(name = "description", length = 2000)
    private String description;

    @OneToMany(mappedBy = "productModal", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FeedbackModal> productFeedbacks = new HashSet<>();

    @OneToMany(mappedBy = "productModal", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PreviewImageModal> previewImageModals = new HashSet<>();

    @OneToMany(mappedBy = "productModal", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FavoriteProductModal> favoriteProductModals = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_category")
    private CategoryModal categoryModal;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_producer")
    private ProducerModal producerModal;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "orders_products",
            joinColumns = @JoinColumn(name = "id_product", referencedColumnName = "id_product", nullable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "id_order", referencedColumnName = "id_order", nullable = false, updatable = false)
    )

    private List<OrderModal> orderMeal = new ArrayList<>();

    @OneToOne(mappedBy = "productMouse", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private MouseProperties mouseProperties;

    @OneToOne(mappedBy = "productLaptop", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private LaptopProperties laptopProperties;

    @OneToOne(mappedBy = "productKeyboard", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private KeyboardProperties keyboardProperties;

    @OneToMany(mappedBy = "productModal")
    private List<CartModal> cartModals;

}

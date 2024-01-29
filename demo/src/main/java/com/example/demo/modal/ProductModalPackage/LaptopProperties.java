package com.example.demo.modal.ProductModalPackage;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "laptopProperties", schema = "gearvn")
public class LaptopProperties {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_lapProperties;
    @Column(name = "cpu")
    private String cpu;
    @Column(name = "vga")
    private String vga;
    @Column(name = "ram")
    private String ram;
    @Column(name = "ssd")
    private String ssd;
    @Column(name = "screen")
    private String screen;
    @Column(name = "size")
    private String size;
    @Column(name = "color")
    private String color;
    @Column(name = "operatingSystem")
    private String operatingSystem;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_product")
    @JsonManagedReference
    private ProductModal productLaptop;

}

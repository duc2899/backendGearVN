package com.example.demo.modal.ProductModalPackage;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_product")
    private ProductModal productLaptop;

}

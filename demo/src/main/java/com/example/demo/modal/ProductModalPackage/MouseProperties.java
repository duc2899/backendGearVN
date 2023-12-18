package com.example.demo.modal.ProductModalPackage;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "mouseProperties", schema = "gearvn")
public class MouseProperties {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_mouseProperties;

    @Column(name = "dpi")
    private String dpi;
    @Column(name = "size")
    private String size;
    @Column(name = "connection")
    private String connection;
    @Column(name = "rgb")
    private String rbg;
    @Column(name = "color")
    private String color;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_product")
    private ProductModal productMouse;


}
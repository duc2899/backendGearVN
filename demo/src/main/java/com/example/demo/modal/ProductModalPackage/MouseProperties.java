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
    private Boolean connection;
    @Column(name = "charger")
    private Boolean charger;
    @Column(name = "rgb")
    private Boolean rgb;
    @Column(name = "color")
    private String color;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_product")
    @JsonManagedReference
    private ProductModal productMouse;

}
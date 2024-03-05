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
@Table(name = "keyBoardProperties", schema = "gearvn")
public class KeyboardProperties {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_keyBoardProperties;

    @Column(name = "size")
    private String size;
    @Column(name = "connection")
    private String connection;
    @Column(name = "charger")
    private String charger;
    @Column(name = "rgb")
    private String rgb;
    @Column(name = "color")
    private String color;
    @Column(name = "expand")
    private String expand;
    @Column(name = "material")
    private String material;
    @Column(name = "switches")
    private String switches;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_product")
    @JsonManagedReference
    private ProductModal productKeyboard;

}
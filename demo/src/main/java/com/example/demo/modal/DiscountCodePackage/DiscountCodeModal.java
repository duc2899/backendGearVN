package com.example.demo.modal.DiscountCodePackage;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "discount", schema = "gearvn")
public class DiscountCodeModal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "reduce_price")
    private int reduce_price;

    @Column(name = "condition_price")
    private int condition_price;

    @Column(name = "code")
    private String code;

    @Column(name = "expiry")
    private LocalDateTime expiry;
}

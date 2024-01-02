package com.example.demo.modal.ProducerPackage;

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
@Table(name = "producer", schema = "gearvn")
public class ProducerModal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_producer;

    @Column(name = "name_producer")
    private String name_producer;

    @OneToMany(mappedBy = "producerModal", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<ProductModal> productModals = new HashSet<>();
}

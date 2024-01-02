package com.example.demo.modal.AddressNotePackage;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ward", schema = "gearvn")
public class WardModal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_ward;

    @Column(name = "code")
    private int code;
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "wardModal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<AddressNoteModal> addressNoteModal;
}

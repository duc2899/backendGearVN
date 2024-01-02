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
@Table(name = "city", schema = "gearvn")
public class CityModal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_city;

    @Column(name = "code")
    private int code;
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "cityModal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<AddressNoteModal> addressNoteModal;

}

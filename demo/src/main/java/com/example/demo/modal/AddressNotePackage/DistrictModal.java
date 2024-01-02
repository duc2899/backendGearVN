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
@Table(name = "district", schema = "gearvn")
public class DistrictModal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_district;

    @Column(name = "code")
    private int code;
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "districtModal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<AddressNoteModal> addressNoteModal;
}

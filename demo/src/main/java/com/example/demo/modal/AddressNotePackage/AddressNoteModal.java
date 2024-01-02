package com.example.demo.modal.AddressNotePackage;

import com.example.demo.modal.UserModalPackage.UserModal;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "address_note", schema = "gearvn")
public class AddressNoteModal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_addressNote;

    @Column(name = "nameCustomer")
    private String nameCustomer;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "homeAddress")
    private String homeAddress;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_ward")
    private WardModal wardModal;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_city")
    private CityModal cityModal;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_district")
    private DistrictModal districtModal;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_user")
    private UserModal userAddressNoteModal;

}

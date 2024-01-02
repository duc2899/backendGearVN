package com.example.demo.repository.AddressNoteRepository;

import com.example.demo.modal.AddressNotePackage.AddressNoteModal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressNoteRepository extends JpaRepository<AddressNoteModal, Integer> {
    @Query(value = "select * from gearvn.address_note where id_user = :id_user", nativeQuery = true)
    List<AddressNoteModal> findAddressNoteByIdUser(@Param("id_user") int id_user);

    @Query(value = "select * from gearvn.address_note where id_user = :id_user and id_address_note = :id_address_note", nativeQuery = true)
    Optional<AddressNoteModal> findAddressNoteByIdUserAndIdAddressNote(@Param("id_user") int id_user, @Param("id_address_note") int id_address_note);

    @Query(value = "select * from gearvn.address_note where id_address_note = :id_address_note", nativeQuery = true)
    AddressNoteModal findAddressNoteById(@Param("id_address_note") int id_address_note);
}

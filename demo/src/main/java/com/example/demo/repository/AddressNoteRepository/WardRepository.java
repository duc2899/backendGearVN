package com.example.demo.repository.AddressNoteRepository;

import com.example.demo.modal.AddressNotePackage.CityModal;
import com.example.demo.modal.AddressNotePackage.WardModal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WardRepository extends JpaRepository<WardModal, Integer> {
    @Query(value = "select * from gearvn.ward where id_ward = :id_ward", nativeQuery = true)
    WardModal findWardById(@Param("id_ward") int id_ward);
}

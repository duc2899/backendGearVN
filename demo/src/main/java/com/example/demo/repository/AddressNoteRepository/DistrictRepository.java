package com.example.demo.repository.AddressNoteRepository;

import com.example.demo.modal.AddressNotePackage.CityModal;
import com.example.demo.modal.AddressNotePackage.DistrictModal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends JpaRepository<DistrictModal, Integer> {
    @Query(value = "select * from gearvn.district where id_district = :id_district", nativeQuery = true)
    DistrictModal findDistrictById(@Param("id_district") int id_district);
}

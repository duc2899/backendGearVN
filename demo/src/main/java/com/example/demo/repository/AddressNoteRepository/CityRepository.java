package com.example.demo.repository.AddressNoteRepository;

import com.example.demo.modal.AddressNotePackage.CityModal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<CityModal, Integer> {
    @Query(value = "select * from gearvn.city where id_city = :id_city", nativeQuery = true)
    CityModal findCityById(@Param("id_city") int id_city);
}

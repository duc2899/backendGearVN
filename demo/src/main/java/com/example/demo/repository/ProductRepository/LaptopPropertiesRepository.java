package com.example.demo.repository.ProductRepository;

import com.example.demo.modal.ProductModalPackage.LaptopProperties;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository

public interface LaptopPropertiesRepository extends JpaRepository<LaptopProperties, Integer>, JpaSpecificationExecutor<LaptopProperties> {

    @Modifying
    @Transactional
    @Query(value = "delete from gearvn.laptop_properties where id_product = :id_product", nativeQuery = true)
    void deleteByIdProduct(@Param("id_product") int id_product);
}

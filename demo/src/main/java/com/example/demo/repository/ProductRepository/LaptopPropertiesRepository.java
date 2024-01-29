package com.example.demo.repository.ProductRepository;

import com.example.demo.modal.ProductModalPackage.LaptopProperties;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface LaptopPropertiesRepository extends JpaRepository<LaptopProperties, Integer>, JpaSpecificationExecutor<LaptopProperties> {
    @Query(value = "select * from gearvn.laptop_properties where id_product = :id_product", nativeQuery = true)
    Optional<LaptopProperties> findProductOptional(@Param("id_product") int id_product);

    @Query(value = "select * from gearvn.laptop_properties where id_product = :id_product", nativeQuery = true)
    LaptopProperties findProduct(@Param("id_product") int id_product);

    @Transactional
    @Modifying
    @Query(value = "delete from gearvn.laptop_properties where id_product = :id_product", nativeQuery = true)
    void deleteProductByIdProduct(@Param("id_product") int id_product);

}

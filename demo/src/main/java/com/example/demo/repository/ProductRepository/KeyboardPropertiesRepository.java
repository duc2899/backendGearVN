package com.example.demo.repository.ProductRepository;

import com.example.demo.modal.ProductModalPackage.KeyboardProperties;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface KeyboardPropertiesRepository extends JpaRepository<KeyboardProperties, Integer>, JpaSpecificationExecutor<KeyboardProperties> {
    @Modifying
    @Transactional
    @Query(value = "delete from gearvn.key_board_properties where id_product = :id_product", nativeQuery = true)
    void deleteByIdProduct(@Param("id_product") int id_product);
}

package com.example.demo.repository.ProductRepository;

import com.example.demo.modal.ProductModalPackage.ProductModal;
import com.example.demo.modal.UserModalPackage.UserModal;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductModal, Integer>, JpaSpecificationExecutor<ProductModal> {
    @Query(value = "select * from gearvn.product where id_category = :id_category", nativeQuery = true)
    List<ProductModal> findProductByIdCategory(@Param("id_category") int id_category);

    @Query(value = "select * from gearvn.product where id_product = :id_product", nativeQuery = true)
    ProductModal findProductById(@Param("id_product") int id_product);

    @Transactional
    @Modifying
    @Query(value = "delete from gearvn.product where id_product = :id_product", nativeQuery = true)
    void deleteProduct(@Param("id_product") int id_product);
}

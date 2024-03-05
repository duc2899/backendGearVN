package com.example.demo.repository.ProductRepository;

import com.example.demo.modal.ProductModalPackage.ProductModal;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductModal, Integer>, JpaSpecificationExecutor<ProductModal> {

    @Query(value = "select * from gearvn.product where title LIKE %:title%", nativeQuery = true)
    List<ProductModal> findListProductByName(@Param("title") String title);

    @Query(value = "select * from gearvn.product where id_product = :id_product", nativeQuery = true)
    ProductModal findProductById(@Param("id_product") int id_product);

    @Query(value = "select * from gearvn.product LIMIT :limit", nativeQuery = true)
    List<ProductModal> findLimitedRecords(@Param("limit") int limit);

    @Query(value = "select * from gearvn.product order by id_product DESC limit 10", nativeQuery = true)
    List<ProductModal> getProductByDESC();

    @Modifying
    @Transactional
    @Query(value = "delete from gearvn.product where id_product = :id_product", nativeQuery = true)
    void deleteByIdProduct(@Param("id_product") int id_product);
}

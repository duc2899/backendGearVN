package com.example.demo.repository.ProductRepository;

import com.example.demo.modal.ProductModalPackage.FavoriteProductModal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FavoriteProductRepository extends JpaRepository<FavoriteProductModal, Integer> {
    @Query(value = "select * from gearvn.favorite_product where id_user = :id_user", nativeQuery = true)
    List<FavoriteProductModal> findFavoriteProductByIdUser(@Param("id_user") int id_user);

    @Query(value = "select * from gearvn.favorite_product where id_user = :id_user and id_product = :id_product", nativeQuery = true)
    Optional<FavoriteProductModal> findFavoriteProductByIdUserAndIdProduct(@Param("id_user") int id_user, @Param("id_product") int id_product);

}

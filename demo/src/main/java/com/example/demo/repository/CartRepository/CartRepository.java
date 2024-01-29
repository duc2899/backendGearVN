package com.example.demo.repository.CartRepository;

import com.example.demo.modal.CartPackage.CartModal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<CartModal, Integer> {
    @Query(value = "select * from gearvn.cart_user where id_user = :id_user", nativeQuery = true)
    List<CartModal> findCartByUser(@Param("id_user") int id_user);

    @Query(value = "select * from gearvn.cart_user where id_product = :id_product and id_user = :id_user", nativeQuery = true)
    CartModal findCartByItemAndUser(@Param("id_product") int id_product, @Param("id_user") int id_user);

}

package com.example.demo.repository.DiscountCodeRepository;

import com.example.demo.modal.DiscountCodePackage.DiscountCodeModal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiscountCodeRepository extends JpaRepository<DiscountCodeModal, Integer> {
    @Query(value = "select * from gearvn.discount where code = :code", nativeQuery = true)
    Optional<DiscountCodeModal> findDiscountCodeByCodeOptional(@Param("code") String code);

    @Query(value = "select * from gearvn.discount where id = :id", nativeQuery = true)
    DiscountCodeModal findDiscountCodeById(@Param("id") int id);

    @Query(value = "select * from gearvn.discount where code = :code", nativeQuery = true)
    DiscountCodeModal findDiscountCodeByCode(@Param("code") String code);

    @Query(value = "select * from gearvn.discount where id != :id and code = :code", nativeQuery = true)
    Optional<DiscountCodeModal> findDiscountCodeByIdSpecial(@Param("id") int id, @Param("code") String code);


}

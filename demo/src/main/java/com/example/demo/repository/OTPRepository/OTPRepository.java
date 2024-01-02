package com.example.demo.repository.OTPRepository;

import com.example.demo.modal.UserModalPackage.OTPModal;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OTPRepository extends JpaRepository<OTPModal, Integer> {
    @Query(value = "select * from gearvn.otp where id_user = :id_user and code = :code", nativeQuery = true)
    OTPModal findOTPByUserAndCode(@Param("id_user") int id_user, @Param("code") String code);

    @Query(value = "select * from gearvn.otp where id_user = :id_user", nativeQuery = true)
    OTPModal findOTPByEmailUser(@Param("id_user") int id_user);

    @Modifying
    @Transactional
    @Query(value = "delete from gearvn.otp where id_otp = :id_otp", nativeQuery = true)
    void deleteOTPById(@Param("id_otp") int id_otp);

    @Query(value = "select * from gearvn.otp where id_user = :id_user", nativeQuery = true)
    Optional<OTPModal> findOTPByEmailUserOptional(@Param("id_user") int id_user);
}

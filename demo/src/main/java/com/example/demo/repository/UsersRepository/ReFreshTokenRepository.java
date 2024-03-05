package com.example.demo.repository.UsersRepository;

import com.example.demo.modal.UserModalPackage.RefreshTokenModal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReFreshTokenRepository extends JpaRepository<RefreshTokenModal, Integer> {
    RefreshTokenModal findByToken(String token);

    @Query(value = "select * from gearvn.refresh_token where id_user = :id_user", nativeQuery = true)
    RefreshTokenModal findTokenByIdUser(@Param("id_user") int id_user);
}

package com.example.demo.repository.UsersRepository;


import com.example.demo.modal.UserModalPackage.UserModal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModal, Integer> {
    @Query(value = "select * from gearvn.users where id_user = :id_user", nativeQuery = true)
    UserModal findUserByID  (@Param("id_user") int id_user);
    @Query(value = "select * from gearvn.users where name = :name", nativeQuery = true)
    Optional<UserModal> findUserByName  (@Param("name") String name);

    @Query(value = "select * from gearvn.users where email = :email", nativeQuery = true)
    Optional<UserModal> findUserByEmailOptional(@Param("email") String email);

    @Query(value = "select * from gearvn.users where email = :email", nativeQuery = true)
    UserModal findUserByEmail(@Param("email") String email);

    @Query(value = "select * from gearvn.users where email = :email and password = :password", nativeQuery = true)
    Optional<UserModal> findUserByEmailAndPass(@Param("email") String email, @Param("password") String password);

    @Query(value = "select * from gearvn.users where id_user = :id_user and password = :password", nativeQuery = true)
    Optional<UserModal> findUserByIDAndPass(@Param("id_user") int id_user, @Param("password") String password);

    @Query(value = "select * from gearvn.users where name = :name and password = :password", nativeQuery = true)
    Optional<UserModal> findUserByNameAndPass(@Param("name") String name, @Param("password") String password);

    UserModal findByVerifyCode(String verifyCode);

    boolean existsByEmail(String email);
}

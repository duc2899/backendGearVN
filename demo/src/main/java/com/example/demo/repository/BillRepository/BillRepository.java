package com.example.demo.repository.BillRepository;

import com.example.demo.modal.BillModalPackage.BillModal;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<BillModal, Integer> {
    @Query(value = "select * from gearvn.bills where :id_bill = id_bill", nativeQuery = true)
    BillModal findBillsByIdBill(@Param("id_bill") int id_bill);

    @Query(value = "select * from gearvn.bills where :user_id = user_id", nativeQuery = true)
    List<BillModal> findBillsByIdUser(@Param("user_id") int user_id);

    @Query(value = "select * from gearvn.bills where :user_id = user_id and id_bill = :id_bill", nativeQuery = true)
    List<BillModal> findBillsByIdUserAndIdBill(@Param("user_id") int user_id, @Param("id_bill") int id_bill);

    @Modifying
    @Transactional
    @Query(value = "update gearvn.bills set status_order = :status_order where id_bill = :id_bill", nativeQuery = true)
    void updateStatusBill(@Param("status_order") int status_order, @Param("id_bill") int id_bill);

    @Modifying
    @Transactional
    @Query(value = "update gearvn.bills set is_pay = :is_pay where id_bill = :id_bill", nativeQuery = true)
    void updateIsPay(@Param("is_pay") boolean is_pay, @Param("id_bill") int id_bill);
}

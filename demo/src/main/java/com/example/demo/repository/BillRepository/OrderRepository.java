package com.example.demo.repository.BillRepository;

import com.example.demo.modal.BillModalPackage.OrderModal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderModal, Integer> {
}

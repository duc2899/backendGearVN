package com.example.demo.repository.ProductRepository;

import com.example.demo.modal.CategoryPackage.CategoryModal;
import com.example.demo.modal.ProducerPackage.ProducerModal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducerRepository extends JpaRepository<ProducerModal, Integer> {

}

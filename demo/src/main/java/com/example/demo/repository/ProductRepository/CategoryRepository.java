package com.example.demo.repository.ProductRepository;

import com.example.demo.modal.CategoryPackage.CategoryModal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryModal, Integer> {

}

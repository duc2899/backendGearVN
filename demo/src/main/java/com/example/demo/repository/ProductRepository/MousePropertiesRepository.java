package com.example.demo.repository.ProductRepository;

import com.example.demo.modal.ProductModalPackage.MouseProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MousePropertiesRepository extends JpaRepository<MouseProperties, Integer>, JpaSpecificationExecutor<MouseProperties> {
}

package com.example.demo.repository.ProductRepository;

import com.example.demo.modal.ProductModalPackage.KeyboardProperties;
import com.example.demo.modal.ProductModalPackage.MouseProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface KeyboardPropertiesRepository extends JpaRepository<MouseProperties, Integer>, JpaSpecificationExecutor<KeyboardProperties> {
}

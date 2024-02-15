package com.example.demo.repository.ProductRepository;

import com.example.demo.modal.ProductModalPackage.LaptopProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository

public interface LaptopPropertiesRepository extends JpaRepository<LaptopProperties, Integer>, JpaSpecificationExecutor<LaptopProperties> {


}

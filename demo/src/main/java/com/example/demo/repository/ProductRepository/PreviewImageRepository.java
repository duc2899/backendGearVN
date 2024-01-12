package com.example.demo.repository.ProductRepository;

import com.example.demo.modal.ProductModalPackage.PreviewImageModal;
import com.example.demo.modal.ProductModalPackage.ProductModal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PreviewImageRepository extends JpaRepository<PreviewImageModal, Integer> {
    @Query(value = "select * from gearvn.preview_image where id_product = :id_product", nativeQuery = true)
    List<PreviewImageModal> findPreviewImageByID(@Param("id_product") int id_product);
}

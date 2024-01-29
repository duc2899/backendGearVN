package com.example.demo.service.PreviewImageService;

import com.example.demo.modal.ProductModalPackage.PreviewImageModal;
import com.example.demo.modal.ProductModalPackage.ProductModal;
import com.example.demo.repository.ProductRepository.PreviewImageRepository;
import com.example.demo.repository.ProductRepository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class PreviewImageService {
    private final PreviewImageRepository previewImageRepository;
    private final ProductRepository productRepository;


    public PreviewImageService(PreviewImageRepository previewImageRepository, ProductRepository productRepository) {
        this.previewImageRepository = previewImageRepository;
        this.productRepository = productRepository;
    }

    public void addPreviewImage(int id, String image, String idImage) {
        PreviewImageModal previewImageModal = new PreviewImageModal();
        ProductModal productModal = productRepository.findProductById(id);
        previewImageModal.setProductModal(productModal);
        previewImageModal.setIdImage(idImage);
        previewImageModal.setImage(image);

        previewImageRepository.save(previewImageModal);
    }
}

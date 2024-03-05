package com.example.demo.utilities;

import com.example.demo.DTO.ProductDTO.FeedbackProductResponseDTO;
import com.example.demo.DTO.ProductDTO.PreviewImageResponseDTO;
import com.example.demo.DTO.ProductDTO.ProductMouseDTO.MouseProductRequestDTO;
import com.example.demo.DTO.ProductDTO.ProductMouseDTO.MousePropertiesDTO;
import com.example.demo.DTO.ProductDTO.ProductResponseDTO;
import com.example.demo.DTO.ProductDTO.PropertiesDTO;
import com.example.demo.modal.CategoryPackage.CategoryModal;
import com.example.demo.modal.FeedbackPackage.FeedbackModal;
import com.example.demo.modal.ProducerPackage.ProducerModal;
import com.example.demo.modal.ProductModalPackage.MouseProperties;
import com.example.demo.modal.ProductModalPackage.PreviewImageModal;
import com.example.demo.modal.ProductModalPackage.ProductModal;
import com.example.demo.repository.FeedbackRepository.FeedbackRepository;
import com.example.demo.repository.ProductRepository.PreviewImageRepository;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class TransferMouseProduct {
    public TransferMouseProduct() {
    }

    public static ProductModal toMouseProduct(MouseProductRequestDTO mouseProductRequestDTO, CategoryModal categoryModal, ProducerModal producerModal) {
        ProductModal productModal = new ProductModal();
        productModal.setCategoryModal(categoryModal);
        productModal.setProducerModal(producerModal);
        BeanUtils.copyProperties(mouseProductRequestDTO, productModal);
        return productModal;
    }

    public static MouseProperties toMouseProperties(MousePropertiesDTO mousePropertiesDTO, ProductModal productModal) {
        MouseProperties mouseProperties = new MouseProperties();
        BeanUtils.copyProperties(mousePropertiesDTO, mouseProperties);
        mouseProperties.setProductMouse(productModal);
        return mouseProperties;
    }

    public static ProductModal toMouseProductDB(MouseProductRequestDTO mouseProductRequestDTO, ProductModal productModal) {
        BeanUtils.copyProperties(mouseProductRequestDTO, productModal);
        return productModal;
    }

    public static MouseProperties toMousePropertiesDB(MousePropertiesDTO mousePropertiesDTO, MouseProperties mouseProperties) {
        BeanUtils.copyProperties(mousePropertiesDTO, mouseProperties);
        return mouseProperties;
    }

    //
    public static ProductResponseDTO toProductDTO(ProductModal productModal, FeedbackRepository feedbackRepository, PreviewImageRepository previewImageRepository) {
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();

        List<FeedbackProductResponseDTO> productFeedback = new ArrayList<>();
        List<PreviewImageResponseDTO> previewImageResponseDTOS = new ArrayList<>();

        List<PropertiesDTO> propertiesDTOS = new ArrayList<>();

        PropertiesDTO propertiesCharger = new PropertiesDTO();
        propertiesCharger.setIsPublic(true);
        propertiesCharger.setId(1);
        propertiesCharger.setName("charger");
        propertiesCharger.setProperties(productModal.getMouseProperties().getCharger());
        propertiesDTOS.add(propertiesCharger);

        PropertiesDTO propertiesRGB = new PropertiesDTO();
        propertiesRGB.setIsPublic(true);
        propertiesRGB.setId(2);
        propertiesRGB.setName("rgb");
        propertiesRGB.setProperties(productModal.getMouseProperties().getRgb());
        propertiesDTOS.add(propertiesRGB);

        PropertiesDTO propertiesConnection = new PropertiesDTO();
        propertiesConnection.setIsPublic(true);
        propertiesConnection.setId(3);
        propertiesConnection.setName("connection");
        propertiesConnection.setProperties(productModal.getMouseProperties().getConnection());
        propertiesDTOS.add(propertiesConnection);

        PropertiesDTO propertiesDPI = new PropertiesDTO();
        propertiesDPI.setIsPublic(false);
        propertiesDPI.setId(4);
        propertiesDPI.setName("dpi");
        propertiesDPI.setProperties(productModal.getMouseProperties().getDpi());
        propertiesDTOS.add(propertiesDPI);

        PropertiesDTO propertiesSize = new PropertiesDTO();
        propertiesSize.setIsPublic(false);
        propertiesSize.setId(5);
        propertiesSize.setName("size");
        propertiesSize.setProperties(productModal.getMouseProperties().getSize());
        propertiesDTOS.add(propertiesSize);

        PropertiesDTO propertiesColor = new PropertiesDTO();
        propertiesColor.setIsPublic(false);
        propertiesColor.setId(6);
        propertiesColor.setName("color");
        propertiesColor.setProperties(productModal.getMouseProperties().getColor());
        propertiesDTOS.add(propertiesColor);

        PropertiesDTO propertiesProducer = new PropertiesDTO();
        propertiesProducer.setIsPublic(false);
        propertiesProducer.setId(7);
        propertiesProducer.setName("producer");
        propertiesProducer.setProperties(productModal.getProducerModal().getName_producer());
        propertiesDTOS.add(propertiesProducer);

        List<FeedbackModal> feedbackModals = feedbackRepository.getFeedbacksByIdProduct(productModal.getId_product());
        for (FeedbackModal feedbackModal : feedbackModals) {
            FeedbackProductResponseDTO feedbackProductResponseDTO = new FeedbackProductResponseDTO();
            feedbackProductResponseDTO.setStar(feedbackModal.getStar());
            productFeedback.add(feedbackProductResponseDTO);
        }
        //Add preview image
        List<PreviewImageModal> previewImageModals = previewImageRepository.findPreviewImageByID(productModal.getId_product());
        for (PreviewImageModal previewImageModal : previewImageModals) {
            PreviewImageResponseDTO previewImageResponseDTO = new PreviewImageResponseDTO();
            previewImageResponseDTO.setImage(previewImageModal.getImage());
            previewImageResponseDTO.setId(previewImageModal.getId());
            previewImageResponseDTOS.add(previewImageResponseDTO);
        }

        productResponseDTO.setPreviewImages(previewImageResponseDTOS);
        productResponseDTO.setDataFeedback(productFeedback);
        productResponseDTO.setProperties(propertiesDTOS);
        productResponseDTO.setProducer(productModal.getProducerModal().getName_producer());
        productResponseDTO.setId(productModal.getId_product());
        productResponseDTO.setType(productModal.getCategoryModal().getName_category());
        BeanUtils.copyProperties(productModal, productResponseDTO);
        return productResponseDTO;
    }
}

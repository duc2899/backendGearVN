package com.example.demo.utilities;

import com.example.demo.DTO.ProductDTO.FeedbackProductResponseDTO;
import com.example.demo.DTO.ProductDTO.PreviewImageResponseDTO;
import com.example.demo.DTO.ProductDTO.ProductKeyBoardDTO.KeyBoardProductRequestDTO;
import com.example.demo.DTO.ProductDTO.ProductKeyBoardDTO.KeyBoardPropertiesDTO;
import com.example.demo.DTO.ProductDTO.ProductResponseDTO;
import com.example.demo.DTO.ProductDTO.PropertiesDTO;
import com.example.demo.modal.CategoryPackage.CategoryModal;
import com.example.demo.modal.FeedbackPackage.FeedbackModal;
import com.example.demo.modal.ProducerPackage.ProducerModal;
import com.example.demo.modal.ProductModalPackage.KeyboardProperties;
import com.example.demo.modal.ProductModalPackage.PreviewImageModal;
import com.example.demo.modal.ProductModalPackage.ProductModal;
import com.example.demo.repository.FeedbackRepository.FeedbackRepository;
import com.example.demo.repository.ProductRepository.PreviewImageRepository;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class TransferKeyBoardProduct {
    public TransferKeyBoardProduct() {

    }

    public static ProductModal toKeyBoardProduct(KeyBoardProductRequestDTO keyBoardProductRequestDTO, CategoryModal categoryModal, ProducerModal producerModal) {
        ProductModal productModal = new ProductModal();
        productModal.setCategoryModal(categoryModal);
        productModal.setProducerModal(producerModal);
        BeanUtils.copyProperties(keyBoardProductRequestDTO, productModal);
        return productModal;
    }

    public static KeyboardProperties toKeyboardProperties(KeyBoardPropertiesDTO keyBoardPropertiesDTO, ProductModal productModal) {
        KeyboardProperties keyboardProperties = new KeyboardProperties();
        BeanUtils.copyProperties(keyBoardPropertiesDTO, keyboardProperties);
        keyboardProperties.setProductKeyboard(productModal);
        return keyboardProperties;
    }

    public static ProductModal toKeyboardProductDB(KeyBoardProductRequestDTO keyBoardPropertiesDTO, ProductModal productModal) {
        BeanUtils.copyProperties(keyBoardPropertiesDTO, productModal);
        return productModal;
    }

    public static KeyboardProperties toKeyboardPropertiesDB(KeyBoardPropertiesDTO keyBoardPropertiesDTO, KeyboardProperties keyboardProperties) {
        BeanUtils.copyProperties(keyBoardPropertiesDTO, keyboardProperties);
        return keyboardProperties;
    }

    public static ProductResponseDTO toProductKeyboardDTO(ProductModal productModal, FeedbackRepository feedbackRepository, PreviewImageRepository previewImageRepository) {
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();

        List<FeedbackProductResponseDTO> productFeedback = new ArrayList<>();
        List<PreviewImageResponseDTO> previewImageResponseDTOS = new ArrayList<>();

        List<PropertiesDTO> propertiesDTOS = new ArrayList<>();

        PropertiesDTO propertiesCharger = new PropertiesDTO();
        propertiesCharger.setIsPublic(true);
        propertiesCharger.setId(1);
        propertiesCharger.setName("charger");
        propertiesCharger.setProperties(productModal.getKeyboardProperties().getCharger() ? "Có" : "Không");
        propertiesDTOS.add(propertiesCharger);

        PropertiesDTO propertiesRGB = new PropertiesDTO();
        propertiesRGB.setIsPublic(true);
        propertiesRGB.setId(2);
        propertiesRGB.setName("rgb");
        propertiesRGB.setProperties(productModal.getKeyboardProperties().getRgb() ? "Có" : "Không");
        propertiesDTOS.add(propertiesRGB);

        PropertiesDTO propertiesConnection = new PropertiesDTO();
        propertiesConnection.setIsPublic(true);
        propertiesConnection.setId(3);
        propertiesConnection.setName("connection");
        propertiesConnection.setProperties(productModal.getKeyboardProperties().getConnection() ? "Có" : "Không");
        propertiesDTOS.add(propertiesConnection);

        PropertiesDTO propertiesDPI = new PropertiesDTO();
        propertiesDPI.setIsPublic(false);
        propertiesDPI.setId(4);
        propertiesDPI.setName("expand");
        propertiesDPI.setProperties(productModal.getKeyboardProperties().getExpand());
        propertiesDTOS.add(propertiesDPI);

        PropertiesDTO propertiesSize = new PropertiesDTO();
        propertiesSize.setIsPublic(false);
        propertiesSize.setId(5);
        propertiesSize.setName("size");
        propertiesSize.setProperties(productModal.getKeyboardProperties().getSize());
        propertiesDTOS.add(propertiesSize);

        PropertiesDTO propertiesColor = new PropertiesDTO();
        propertiesColor.setIsPublic(false);
        propertiesColor.setId(6);
        propertiesColor.setName("color");
        propertiesColor.setProperties(productModal.getKeyboardProperties().getColor());
        propertiesDTOS.add(propertiesColor);

        PropertiesDTO propertiesProducer = new PropertiesDTO();
        propertiesProducer.setIsPublic(false);
        propertiesProducer.setId(7);
        propertiesProducer.setName("producer");
        propertiesProducer.setProperties(productModal.getProducerModal().getName_producer());
        propertiesDTOS.add(propertiesProducer);

        PropertiesDTO propertiesMaterial = new PropertiesDTO();
        propertiesMaterial.setIsPublic(false);
        propertiesMaterial.setId(8);
        propertiesMaterial.setName("material");
        propertiesMaterial.setProperties(productModal.getKeyboardProperties().getMaterial());
        propertiesDTOS.add(propertiesMaterial);

        PropertiesDTO propertiesSwitches = new PropertiesDTO();
        propertiesSwitches.setIsPublic(false);
        propertiesSwitches.setId(9);
        propertiesSwitches.setName("switches");
        propertiesSwitches.setProperties(productModal.getKeyboardProperties().getSwitches());
        propertiesDTOS.add(propertiesSwitches);

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

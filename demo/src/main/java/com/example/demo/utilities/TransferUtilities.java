package com.example.demo.utilities;

import com.example.demo.DTO.AcccountDTO.AccountUserDTO;
import com.example.demo.DTO.ProductDTO.*;
import com.example.demo.DTO.ProductDTO.ProductLaptopDTO.*;
import com.example.demo.modal.CategoryPackage.CategoryModal;
import com.example.demo.modal.FeedbackPackage.FeedbackModal;
import com.example.demo.modal.ProducerPackage.ProducerModal;
import com.example.demo.modal.ProductModalPackage.PreviewImageModal;
import com.example.demo.modal.ProductModalPackage.ProductModal;
import com.example.demo.modal.ProductModalPackage.LaptopProperties;
import com.example.demo.modal.UserModalPackage.UserModal;
import com.example.demo.repository.FeedbackRepository.FeedbackRepository;
import com.example.demo.repository.ProductRepository.PreviewImageRepository;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class TransferUtilities {

    public TransferUtilities() {

    }


    public static AccountUserDTO toUserDTO(UserModal userModal) {
        AccountUserDTO accountUserDTO = new AccountUserDTO();
        accountUserDTO.setId(userModal.getId_user());
        accountUserDTO.setRole(userModal.getRole());
        accountUserDTO.setEmail(userModal.getEmail());
        accountUserDTO.setPhoneNumber(userModal.getPhoneNumber());
        accountUserDTO.setCreatedAt(userModal.getCreatedDate());
        accountUserDTO.setName(userModal.getName());
        accountUserDTO.setPassword(userModal.getPassword());
        accountUserDTO.setIsActive(userModal.getIsActive());
        return accountUserDTO;
    }

    public static LaptopProperties toLapTopProperties(LaptopPropertiesDTO laptopPropertiesDTO, ProductModal productModal) {
        LaptopProperties laptopProperties = new LaptopProperties();
        BeanUtils.copyProperties(laptopPropertiesDTO, laptopProperties);
        laptopProperties.setProductLaptop(productModal);
        return laptopProperties;
    }

    public static LaptopPropertiesDTO toLapTopPropertiesDTO(LaptopProperties laptopProperties) {
        LaptopPropertiesDTO laptopPropertiesDTO = new LaptopPropertiesDTO();
        BeanUtils.copyProperties(laptopProperties, laptopPropertiesDTO);
        return laptopPropertiesDTO;
    }

    private static LaptopPropertiesDTO toLaptopPropertiesDTO(LaptopProperties laptopProperties) {
        LaptopPropertiesDTO laptopPropertiesDTO = new LaptopPropertiesDTO();
        BeanUtils.copyProperties(laptopProperties, laptopPropertiesDTO);
        return laptopPropertiesDTO;
    }


    public static LaptopProductDTO toLaptopProductResponseDTO(ProductModal productModal, FeedbackRepository feedbackRepository, PreviewImageRepository previewImageRepository) {
        LaptopProductDTO laptopProductResponseDTO = new LaptopProductDTO();
        List<FeedbackProductResponseDTO> productFeedback = new ArrayList<>();
        List<PreviewImageResponseDTO> previewImageResponseDTOS = new ArrayList<>();

        List<PropertiesDTO> propertiesDTOS = new ArrayList<>();
        PropertiesDTO propertiesCPU = new PropertiesDTO();
        propertiesCPU.setIsPublic(true);
        propertiesCPU.setId(1);
        propertiesCPU.setName("CPU");
        propertiesCPU.setProperties(productModal.getLaptopProperties().getCpu());
        propertiesDTOS.add(propertiesCPU);

        PropertiesDTO propertiesRam = new PropertiesDTO();
        propertiesRam.setIsPublic(true);
        propertiesRam.setId(2);
        propertiesRam.setName("RAM");
        propertiesRam.setProperties(productModal.getLaptopProperties().getRam());
        propertiesDTOS.add(propertiesRam);

        PropertiesDTO propertiesVga = new PropertiesDTO();
        propertiesVga.setIsPublic(true);
        propertiesVga.setId(3);
        propertiesVga.setName("VGA");
        propertiesVga.setProperties(productModal.getLaptopProperties().getVga());
        propertiesDTOS.add(propertiesVga);

        PropertiesDTO propertiesSsd = new PropertiesDTO();
        propertiesSsd.setIsPublic(true);
        propertiesSsd.setId(4);
        propertiesSsd.setName("SSD");
        propertiesSsd.setProperties(productModal.getLaptopProperties().getSsd());
        propertiesDTOS.add(propertiesSsd);

        PropertiesDTO propertiesScreen = new PropertiesDTO();
        propertiesScreen.setIsPublic(true);
        propertiesScreen.setId(5);
        propertiesScreen.setName("SCREEN");
        propertiesScreen.setProperties(productModal.getLaptopProperties().getScreen());
        propertiesDTOS.add(propertiesScreen);

        PropertiesDTO propertiesSize = new PropertiesDTO();
        propertiesSize.setIsPublic(false);
        propertiesSize.setId(6);
        propertiesSize.setName("SIZE");
        propertiesSize.setProperties(productModal.getLaptopProperties().getSize());
        propertiesDTOS.add(propertiesSize);

        PropertiesDTO propertiesColor = new PropertiesDTO();
        propertiesColor.setIsPublic(false);
        propertiesColor.setId(7);
        propertiesColor.setName("COLOR");
        propertiesColor.setProperties(productModal.getLaptopProperties().getColor());
        propertiesDTOS.add(propertiesColor);

        PropertiesDTO propertiesOperatingSystem = new PropertiesDTO();
        propertiesOperatingSystem.setIsPublic(false);
        propertiesOperatingSystem.setId(8);
        propertiesOperatingSystem.setName("OPERATINGSYSTEM");
        propertiesOperatingSystem.setProperties(productModal.getLaptopProperties().getOperatingSystem());
        propertiesDTOS.add(propertiesOperatingSystem);

        List<FeedbackModal> feedbackModals = feedbackRepository.getFeedbacksByIdProduct(productModal.getId_product());
        for (FeedbackModal feedbackModal : feedbackModals) {
            FeedbackProductResponseDTO feedbackProductResponseDTO = new FeedbackProductResponseDTO();
            feedbackProductResponseDTO.setStar(feedbackModal.getStar());
            feedbackProductResponseDTO.setMessage(feedbackModal.getMessage());
            feedbackProductResponseDTO.setCreatedAt(feedbackModal.getCreatedDate());
            feedbackProductResponseDTO.setName(feedbackModal.getUserModal().getName());
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
        laptopProductResponseDTO.setPreviewImages(previewImageResponseDTOS);
        laptopProductResponseDTO.setDataFeedback(productFeedback);
        laptopProductResponseDTO.setProperties(propertiesDTOS);
        laptopProductResponseDTO.setId(productModal.getId_product());
        laptopProductResponseDTO.setType(productModal.getCategoryModal().getName_category());
        BeanUtils.copyProperties(productModal, laptopProductResponseDTO);
        return laptopProductResponseDTO;
    }

    public static ProductModal toLaptopProduct(LaptopProductRequestDTO laptopProductRequestDTO, CategoryModal categoryModal, ProducerModal producerModal) {
        ProductModal productModal = new ProductModal();
        productModal.setCategoryModal(categoryModal);
        productModal.setProducerModal(producerModal);
        BeanUtils.copyProperties(laptopProductRequestDTO, productModal);

        return productModal;
    }

    public static ProductModal toLaptopProduct(EditLaptopProductRequestDTO editLaptopProductRequestDTO, CategoryModal categoryModal) {
        ProductModal productModal = new ProductModal();
        productModal.setCategoryModal(categoryModal);
        BeanUtils.copyProperties(editLaptopProductRequestDTO, productModal);

        return productModal;
    }


    public static ProductModal toLaptopDB(LaptopProductRequestDTO laptopProductRequestDTO, ProductModal productModalDB, CategoryModal categoryModal) {
        productModalDB.setCategoryModal(categoryModal);
        BeanUtils.copyProperties(laptopProductRequestDTO, productModalDB);
        return productModalDB;
    }


}

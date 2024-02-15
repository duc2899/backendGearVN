package com.example.demo.utilities;

import com.example.demo.DTO.AcccountDTO.AccountUserDTO;
import com.example.demo.DTO.ProductDTO.FeedbackProductResponseDTO;
import com.example.demo.DTO.ProductDTO.PreviewImageResponseDTO;
import com.example.demo.DTO.ProductDTO.ProductLaptopDTO.LaptopProductRequestDTO;
import com.example.demo.DTO.ProductDTO.ProductLaptopDTO.LaptopPropertiesDTO;
import com.example.demo.DTO.ProductDTO.ProductResponseDTO;
import com.example.demo.DTO.ProductDTO.PropertiesDTO;
import com.example.demo.modal.CategoryPackage.CategoryModal;
import com.example.demo.modal.FeedbackPackage.FeedbackModal;
import com.example.demo.modal.ProducerPackage.ProducerModal;
import com.example.demo.modal.ProductModalPackage.LaptopProperties;
import com.example.demo.modal.ProductModalPackage.PreviewImageModal;
import com.example.demo.modal.ProductModalPackage.ProductModal;
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

    public static LaptopProperties toLapTopPropertiesDB(LaptopPropertiesDTO laptopPropertiesDTO, LaptopProperties laptopProperties) {
        BeanUtils.copyProperties(laptopPropertiesDTO, laptopProperties);
        return laptopProperties;
    }

    public static ProductResponseDTO toLaptopProductResponseDTO(ProductModal productModal, FeedbackRepository feedbackRepository, PreviewImageRepository previewImageRepository) {
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();

        List<FeedbackProductResponseDTO> productFeedback = new ArrayList<>();
        List<PreviewImageResponseDTO> previewImageResponseDTOS = new ArrayList<>();

        List<PropertiesDTO> propertiesDTOS = new ArrayList<>();

        PropertiesDTO propertiesCPU = new PropertiesDTO();
        propertiesCPU.setIsPublic(true);
        propertiesCPU.setId(1);
        propertiesCPU.setName("cpu");
        propertiesCPU.setProperties(productModal.getLaptopProperties().getCpu());
        propertiesDTOS.add(propertiesCPU);

        PropertiesDTO propertiesRam = new PropertiesDTO();
        propertiesRam.setIsPublic(true);
        propertiesRam.setId(2);
        propertiesRam.setName("ram");
        propertiesRam.setProperties(productModal.getLaptopProperties().getRam());
        propertiesDTOS.add(propertiesRam);

        PropertiesDTO propertiesVga = new PropertiesDTO();
        propertiesVga.setIsPublic(true);
        propertiesVga.setId(3);
        propertiesVga.setName("vga");
        propertiesVga.setProperties(productModal.getLaptopProperties().getVga());
        propertiesDTOS.add(propertiesVga);

        PropertiesDTO propertiesSsd = new PropertiesDTO();
        propertiesSsd.setIsPublic(true);
        propertiesSsd.setId(4);
        propertiesSsd.setName("ssd");
        propertiesSsd.setProperties(productModal.getLaptopProperties().getSsd());
        propertiesDTOS.add(propertiesSsd);

        PropertiesDTO propertiesScreen = new PropertiesDTO();
        propertiesScreen.setIsPublic(true);
        propertiesScreen.setId(5);
        propertiesScreen.setName("screen");
        propertiesScreen.setProperties(productModal.getLaptopProperties().getScreen());
        propertiesDTOS.add(propertiesScreen);

        PropertiesDTO propertiesSize = new PropertiesDTO();
        propertiesSize.setIsPublic(false);
        propertiesSize.setId(6);
        propertiesSize.setName("size");
        propertiesSize.setProperties(productModal.getLaptopProperties().getSize());
        propertiesDTOS.add(propertiesSize);

        PropertiesDTO propertiesColor = new PropertiesDTO();
        propertiesColor.setIsPublic(false);
        propertiesColor.setId(7);
        propertiesColor.setName("color");
        propertiesColor.setProperties(productModal.getLaptopProperties().getColor());
        propertiesDTOS.add(propertiesColor);

        PropertiesDTO propertiesOperatingSystem = new PropertiesDTO();
        propertiesOperatingSystem.setIsPublic(false);
        propertiesOperatingSystem.setId(8);
        propertiesOperatingSystem.setName("operatingSystem");
        propertiesOperatingSystem.setProperties(productModal.getLaptopProperties().getOperatingSystem());
        propertiesDTOS.add(propertiesOperatingSystem);

        PropertiesDTO propertiesProducer = new PropertiesDTO();
        propertiesProducer.setIsPublic(false);
        propertiesProducer.setId(9);
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
        productResponseDTO.setProducer(productModal.getProducerModal().getName_producer());
        productResponseDTO.setProperties(propertiesDTOS);
        productResponseDTO.setId(productModal.getId_product());
        productResponseDTO.setType(productModal.getCategoryModal().getName_category());
        BeanUtils.copyProperties(productModal, productResponseDTO);
        return productResponseDTO;
    }

    public static ProductModal toLaptopProduct(LaptopProductRequestDTO laptopProductRequestDTO, CategoryModal categoryModal, ProducerModal producerModal) {
        ProductModal productModal = new ProductModal();
        productModal.setCategoryModal(categoryModal);
        productModal.setProducerModal(producerModal);
        BeanUtils.copyProperties(laptopProductRequestDTO, productModal);

        return productModal;
    }


    public static ProductModal toLaptopDB(LaptopProductRequestDTO laptopProductRequestDTO, ProductModal productModalDB) {
        BeanUtils.copyProperties(laptopProductRequestDTO, productModalDB);
        return productModalDB;
    }


}

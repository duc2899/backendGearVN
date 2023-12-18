package com.example.demo.utilities;

import com.example.demo.DTO.AcccountDTO.AccountUserDTO;
import com.example.demo.DTO.ProductDTO.EditLaptopProductRequestDTO;
import com.example.demo.DTO.ProductDTO.LaptopProductRequestDTO;
import com.example.demo.DTO.ProductDTO.LaptopProductResponseDTO;
import com.example.demo.DTO.ProductDTO.LaptopPropertiesDTO;
import com.example.demo.modal.CategoryPackage.CategoryModal;
import com.example.demo.modal.ProductModalPackage.ProductModal;
import com.example.demo.modal.ProductModalPackage.LaptopProperties;
import com.example.demo.modal.UserModalPackage.UserModal;
import com.example.demo.repository.ProductRepository.CategoryRepository;
import org.springframework.beans.BeanUtils;

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

    private static LaptopPropertiesDTO toLaptopPropertiesDTO(LaptopProperties laptopProperties) {
        LaptopPropertiesDTO laptopPropertiesDTO = new LaptopPropertiesDTO();
        BeanUtils.copyProperties(laptopProperties, laptopPropertiesDTO);
        return laptopPropertiesDTO;
    }


    public static LaptopProductResponseDTO toLaptopProductResponseDTO(ProductModal productModal) {
        LaptopProductResponseDTO laptopProductResponseDTO = new LaptopProductResponseDTO();
//        laptopProductResponseDTO.setLaptopProperties(toLaptopPropertiesDTO(productModal.getLaptopProperties()));
        laptopProductResponseDTO.setId(productModal.getId_product());
        laptopProductResponseDTO.setName_category(productModal.getCategoryModal().getName_category());
        BeanUtils.copyProperties(productModal, laptopProductResponseDTO);
        return laptopProductResponseDTO;
    }

    public static ProductModal toLaptopProduct(LaptopProductRequestDTO laptopProductRequestDTO, CategoryModal categoryModal) {
        ProductModal productModal = new ProductModal();
        productModal.setCategoryModal(categoryModal);
        BeanUtils.copyProperties(laptopProductRequestDTO, productModal);

        return productModal;
    }

    public static ProductModal toLaptopProduct(EditLaptopProductRequestDTO editLaptopProductRequestDTO, CategoryModal categoryModal) {
        ProductModal productModal = new ProductModal();
        productModal.setCategoryModal(categoryModal);
        BeanUtils.copyProperties(editLaptopProductRequestDTO, productModal);

        return productModal;
    }


    public static ProductModal toLaptopDB(LaptopProductRequestDTO laptopProductRequestDTO, ProductModal productModalDB, CategoryModal categoryModal){
        productModalDB.setCategoryModal(categoryModal);
        BeanUtils.copyProperties(laptopProductRequestDTO, productModalDB);
        return productModalDB;
    }


}

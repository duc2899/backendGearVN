package com.example.demo.service.ProductServices;

import com.example.demo.DTO.ProductDTO.EditLaptopProductRequestDTO;
import com.example.demo.DTO.ProductDTO.LaptopProductRequestDTO;
import com.example.demo.DTO.ProductDTO.LaptopProductResponseDTO;
import com.example.demo.modal.CategoryPackage.CategoryModal;
import com.example.demo.modal.ProductModalPackage.LaptopProperties;
import com.example.demo.modal.ProductModalPackage.ProductModal;
import com.example.demo.repository.ProductRepository.CategoryRepository;
import com.example.demo.repository.ProductRepository.LaptopPropertiesRepository;
import com.example.demo.repository.ProductRepository.ProductRepository;
import com.example.demo.utilities.TransferUtilities;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LaptopProductServices {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    private final LaptopPropertiesRepository laptopPropertiesRepository;

    public List<LaptopProductResponseDTO> getAllProduct() {
        List<ProductModal> listProduct = productRepository.findAll();
        List<LaptopProductResponseDTO> result = new ArrayList<>();
        for (ProductModal productModal : listProduct) {
            result.add(TransferUtilities.toLaptopProductResponseDTO(productModal));
        }
        return result;
    }

    public String addLaptopProduct(LaptopProductRequestDTO laptopProductRequestDTO) {
        if(!checkInput(laptopProductRequestDTO).equals("valid")){
            return checkInput(laptopProductRequestDTO);
        }
        Optional<CategoryModal> categoryModal = categoryRepository.findById(laptopProductRequestDTO.getId_category());
        ProductModal product = TransferUtilities.toLaptopProduct(laptopProductRequestDTO, categoryModal.get());
        product.setLaptopProperties(TransferUtilities.toLapTopProperties(laptopProductRequestDTO.getLaptopProperties(), product));
        productRepository.save(product);
        return "success";
    }

    public String removeLaptopProduct(int idLap) {
        if (checkExitsProduct(idLap)) {
            laptopPropertiesRepository.deleteProductByIdProduct(idLap);
            productRepository.deleteProduct(idLap);
            return "success";
        }
        return "Not found product";
    }

    public String editLaptopProduct(int id, LaptopProductRequestDTO laptopProductRequestDTO) {
        if (checkExitsProduct(id)) {
            if (laptopProductRequestDTO.getLaptopProperties().isLaptopPropertiesNull()) {
                return "Invalid properties laptop";
            }
            if(!checkInput(laptopProductRequestDTO).equals("valid")){
                return checkInput(laptopProductRequestDTO);
            }
            ProductModal productModalDB = productRepository.findProductById(id);
            Optional<CategoryModal> categoryModal = categoryRepository.findById(laptopProductRequestDTO.getId_category());
            ProductModal newProductModal = TransferUtilities.toLaptopDB(laptopProductRequestDTO, productModalDB, categoryModal.get());
            LaptopProperties newLaptopProperties = newProductModal.getLaptopProperties();
            BeanUtils.copyProperties(laptopProductRequestDTO.getLaptopProperties(), newLaptopProperties);
            newProductModal.setLaptopProperties(newLaptopProperties);
            productRepository.save(newProductModal);
            return "success";
        }
        return "Not found product";
    }

    private boolean checkExitsProduct(int id) {
        Optional<ProductModal> productModal = productRepository.findById(id);
        return productModal.isPresent();
    }

    private String checkInput(LaptopProductRequestDTO laptopProductRequestDTO){
        if(Objects.equals(laptopProductRequestDTO.getTitle(), "") || laptopProductRequestDTO.getTitle() == null){
            return "Title is required";
        }
        if(laptopProductRequestDTO.getSaleRate() == null || laptopProductRequestDTO.getSaleRate() > 1 || laptopProductRequestDTO.getSaleRate() < 0){
            return "Sale rate must be about 0 - 1";
        }
        if(laptopProductRequestDTO.getQuantity() == null || laptopProductRequestDTO.getQuantity() <= 0){
            return "Quantity must bigger than 0";
        }
        if(laptopProductRequestDTO.getOldPrice() == null || laptopProductRequestDTO.getOldPrice() <= 0){
            return "Price must be bigger than 0";
        }
        return "valid";
    }

}

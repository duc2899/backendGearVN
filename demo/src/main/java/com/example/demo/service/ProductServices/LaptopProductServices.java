package com.example.demo.service.ProductServices;

import com.example.demo.DTO.ProductDTO.ProductLaptopDTO.LaptopProductDTO;
import com.example.demo.DTO.ProductDTO.ProductLaptopDTO.LaptopProductRequestDTO;
import com.example.demo.DTO.ProductDTO.ProductLaptopDTO.LaptopProductResponseDTO;
import com.example.demo.modal.CategoryPackage.CategoryModal;
import com.example.demo.modal.ProducerPackage.ProducerModal;
import com.example.demo.modal.ProductModalPackage.LaptopProperties;
import com.example.demo.modal.ProductModalPackage.ProductModal;
import com.example.demo.repository.FeedbackRepository.FeedbackRepository;
import com.example.demo.repository.ProductRepository.*;
import com.example.demo.utilities.TransferUtilities;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
    private final FeedbackRepository feedbackRepository;
    private final LaptopPropertiesRepository laptopPropertiesRepository;
    private final PreviewImageRepository previewImageRepository;
    private final ProducerRepository producerRepository;


    public LaptopProductResponseDTO getAllProduct(int pageNo, int pageSize) {
//        Sort sort = sortDir.equals(Sort.Direction.ASC.name()) ? Sort.by(String.valueOf(sortBy)).ascending()
//                : Sort.by(String.valueOf(sortBy)).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<ProductModal> listProduct = productRepository.findAll(isPremium(), pageable);
        List<LaptopProductDTO> laptopProductDTOS = new ArrayList<>();
        LaptopProductResponseDTO result = new LaptopProductResponseDTO();
        for (ProductModal productModal : listProduct) {
            laptopProductDTOS.add(TransferUtilities.toLaptopProductResponseDTO(productModal, feedbackRepository, previewImageRepository));
        }
        result.setData(laptopProductDTOS);
        result.setPageNo(listProduct.getNumber());
        result.setPageSize(listProduct.getSize());
        result.setTotalPages(listProduct.getTotalPages());
        result.setLast(listProduct.isLast());
        result.setTotalElements(listProduct.getTotalElements());
        return result;
    }

    private Specification<ProductModal> isPremium() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("categoryModal").get("id_category"), 1);
    }

    public LaptopProductDTO getProductById(int id) {
        ProductModal productModal = productRepository.findProductById(id);
        return TransferUtilities.toLaptopProductResponseDTO(productModal, feedbackRepository, previewImageRepository);
    }

    public String addLaptopProduct(LaptopProductRequestDTO laptopProductRequestDTO) {
        if (!checkInput(laptopProductRequestDTO).equals("valid")) {
            return checkInput(laptopProductRequestDTO);
        }
        Optional<CategoryModal> categoryModal = categoryRepository.findById(laptopProductRequestDTO.getIdCategory());
        Optional<ProducerModal> producerModal = producerRepository.findById(laptopProductRequestDTO.getIdProducer());
        ProductModal product = TransferUtilities.toLaptopProduct(laptopProductRequestDTO, categoryModal.get(), producerModal.get());
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
            Optional<CategoryModal> categoryModal = categoryRepository.findById(laptopProductRequestDTO.getIdCategory());
            ProductModal newProductModal = TransferUtilities.toLaptopDB(laptopProductRequestDTO, productModalDB, categoryModal.get());
            LaptopProperties newLaptopProperties = newProductModal.getLaptopProperties();
            BeanUtils.copyProperties(laptopProductRequestDTO.getLaptopProperties(), newLaptopProperties);
            newProductModal.setLaptopProperties(newLaptopProperties);
            productRepository.save(newProductModal);
            return "success";
        }
        return "Not found product";
    }

    public String uploadImage(String url, int id) {
        if (!productRepository.existsById(id)) {
            return "Not found id product";
        }
        ProductModal productModal = productRepository.findProductById(id);
        productModal.setImage(url);
        productRepository.saveAndFlush(productModal);
        return "success";
    }

    public boolean checkExitsProduct(int id) {
        Optional<ProductModal> productModal = productRepository.findById(id);
        return productModal.isPresent();
    }

    private String checkInput(LaptopProductRequestDTO laptopProductRequestDTO) {
        if (Objects.equals(laptopProductRequestDTO.getTitle(), "") || laptopProductRequestDTO.getTitle() == null) {
            return "Title is required";
        }
        if (laptopProductRequestDTO.getSaleRate() == null || laptopProductRequestDTO.getSaleRate() > 1 || laptopProductRequestDTO.getSaleRate() < 0) {
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

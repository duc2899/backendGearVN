package com.example.demo.controller.SearchServices;

import com.example.demo.DTO.ProductDTO.ListProductResponseDTO;
import com.example.demo.DTO.ProductDTO.ProductResponseDTO;
import com.example.demo.DTO.ProductDTO.SearchProductDTO.SearchProductKeyBoardRequestDTO;
import com.example.demo.DTO.ProductDTO.SearchProductDTO.SearchProductLaptopRequestDTO;
import com.example.demo.DTO.ProductDTO.SearchProductDTO.SearchProductMouseRequestDTO;
import com.example.demo.modal.ProductModalPackage.*;
import com.example.demo.repository.FeedbackRepository.FeedbackRepository;
import com.example.demo.repository.ProductRepository.*;
import com.example.demo.utilities.TransferKeyBoardProduct;
import com.example.demo.utilities.TransferMouseProduct;
import com.example.demo.utilities.TransferUtilities;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final LaptopPropertiesRepository laptopPropertiesRepository;
    private final MousePropertiesRepository mousePropertiesRepository;
    private final KeyboardPropertiesRepository keyboardPropertiesRepository;
    private final ProductRepository productRepository;
    private final FeedbackRepository feedbackRepository;
    private final PreviewImageRepository previewImageRepository;

    public ListProductResponseDTO sortProduct(String sort, int pageNo, int pageSize, ProductType type) {
        Pageable pageable;
        if (Objects.equals(sort, "descending")) {
            pageable = PageRequest.of(pageNo, pageSize, Sort.by("oldPrice").descending());
        } else {
            pageable = PageRequest.of(pageNo, pageSize, Sort.by("oldPrice").ascending());
        }
        Page<ProductModal> productModals = productRepository.findAll(isPremium(type), pageable);
        ListProductResponseDTO result = new ListProductResponseDTO();
        List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();

        if (type == ProductType.mouse) {
            for (ProductModal productModal : productModals) {
                productResponseDTOS.add(TransferMouseProduct.toProductDTO(productModal, feedbackRepository, previewImageRepository));
            }
        }
        if (type == ProductType.laptop) {
            for (ProductModal productModal : productModals) {
                productResponseDTOS.add(TransferUtilities.toLaptopProductResponseDTO(productModal, feedbackRepository, previewImageRepository));
            }
        }
        if (type == ProductType.keyboard) {
            for (ProductModal productModal : productModals) {
                productResponseDTOS.add(TransferKeyBoardProduct.toProductKeyboardDTO(productModal, feedbackRepository, previewImageRepository));
            }
        }
        result.setData(productResponseDTOS);
        result.setPageNo(productModals.getNumber());
        result.setPageSize(productModals.getSize());
        result.setTotalPages(productModals.getTotalPages());
        result.setLast(productModals.isLast());
        result.setTotalElements(productModals.getTotalElements());
        return result;
    }

    public ListProductResponseDTO searchProductLaptop(SearchProductLaptopRequestDTO searchProductLaptopRequestDTO, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<LaptopProperties> laptopPropertiesList = laptopPropertiesRepository.findAll(filterTotalLaptop(searchProductLaptopRequestDTO), pageable);

        ListProductResponseDTO result = new ListProductResponseDTO();
        List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();
        List<ProductModal> productModalList = new ArrayList<>();
        for (LaptopProperties laptopProperties : laptopPropertiesList) {
            productModalList.add(laptopProperties.getProductLaptop());
        }
        for (ProductModal productModal : productModalList) {
            productResponseDTOS.add(TransferUtilities.toLaptopProductResponseDTO(productModal, feedbackRepository, previewImageRepository));
        }
        result.setData(productResponseDTOS);
        result.setPageNo(laptopPropertiesList.getNumber());
        result.setPageSize(laptopPropertiesList.getSize());
        result.setTotalPages(laptopPropertiesList.getTotalPages());
        result.setLast(laptopPropertiesList.isLast());
        result.setTotalElements(laptopPropertiesList.getTotalElements());
        return result;
    }

    public ListProductResponseDTO searchProductMouse(SearchProductMouseRequestDTO searchProductMouseRequestDTO, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<MouseProperties> mousePropertiesPage = mousePropertiesRepository.findAll(filterTotalMouse(searchProductMouseRequestDTO), pageable);

        ListProductResponseDTO result = new ListProductResponseDTO();
        List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();
        List<ProductModal> productModalList = new ArrayList<>();
        for (MouseProperties mouseProperties : mousePropertiesPage) {
            productModalList.add(mouseProperties.getProductMouse());
        }
        for (ProductModal productModal : productModalList) {
            productResponseDTOS.add(TransferMouseProduct.toProductDTO(productModal, feedbackRepository, previewImageRepository));
        }
        result.setData(productResponseDTOS);
        result.setPageNo(mousePropertiesPage.getNumber());
        result.setPageSize(mousePropertiesPage.getSize());
        result.setTotalPages(mousePropertiesPage.getTotalPages());
        result.setLast(mousePropertiesPage.isLast());
        result.setTotalElements(mousePropertiesPage.getTotalElements());
        return result;
    }

    public ListProductResponseDTO searchProductKeyboard(SearchProductKeyBoardRequestDTO searchProductKeyBoardRequestDTO, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<KeyboardProperties> keyboardPropertiesPage = keyboardPropertiesRepository.findAll(filterTotalKeyboard(searchProductKeyBoardRequestDTO), pageable);

        ListProductResponseDTO result = new ListProductResponseDTO();
        List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();
        List<ProductModal> productModalList = new ArrayList<>();
        for (KeyboardProperties keyboardProperties1 : keyboardPropertiesPage) {
            productModalList.add(keyboardProperties1.getProductKeyboard());
        }
        for (ProductModal productModal : productModalList) {
            productResponseDTOS.add(TransferKeyBoardProduct.toProductKeyboardDTO(productModal, feedbackRepository, previewImageRepository));
        }
        result.setData(productResponseDTOS);
        result.setPageNo(keyboardPropertiesPage.getNumber());
        result.setPageSize(keyboardPropertiesPage.getSize());
        result.setTotalPages(keyboardPropertiesPage.getTotalPages());
        result.setLast(keyboardPropertiesPage.isLast());
        result.setTotalElements(keyboardPropertiesPage.getTotalElements());
        return result;
    }

    private Specification<MouseProperties> filterMouseProperties(List<String> property, String type) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.in(root.get(type)).value(property));
    }

    private Specification<LaptopProperties> filterMLaptopProperties(List<String> property, String type) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.in(root.get(type)).value(property));
    }

    private Specification<KeyboardProperties> filterMKeyboardProperties(List<String> property, String type) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.in(root.get(type)).value(property));
    }

    private Specification<MouseProperties> filterTotalMouse(SearchProductMouseRequestDTO searchMouse) {
        return Specification.where(filterMouseProperties(searchMouse.getDpi(), "dpi").or(filterMouseProperties(searchMouse.getSize(), "size").or(filterMouseProperties(searchMouse.getColor(), "color").or(filterMouseProperties(searchMouse.getConnection(), "connection").or(filterMouseProperties(searchMouse.getCharger(), "charger").or(filterMouseProperties(searchMouse.getRgb(), "rgb")))))));
    }

    private Specification<LaptopProperties> filterTotalLaptop(SearchProductLaptopRequestDTO modal) {
        return Specification.where(filterMLaptopProperties(modal.getCpu(), "cpu").or(filterMLaptopProperties(modal.getRam(), "ram").or(filterMLaptopProperties(modal.getVga(), "vga").or(filterMLaptopProperties(modal.getSsd(), "ssd").or(filterMLaptopProperties(modal.getColor(), "color")).or(filterMLaptopProperties(modal.getOperatingSystem(), "operatingSystem").or(filterMLaptopProperties(modal.getSize(), "size").or(filterMLaptopProperties(modal.getScreen(), "screen"))))))));
    }

    private Specification<KeyboardProperties> filterTotalKeyboard(SearchProductKeyBoardRequestDTO searchMouse) {
        return Specification.allOf(filterMKeyboardProperties(searchMouse.getSwitches(), "switches").or(filterMKeyboardProperties(searchMouse.getSize(), "size").or(filterMKeyboardProperties(searchMouse.getColor(), "color").or(filterMKeyboardProperties(searchMouse.getConnection(), "connection").or(filterMKeyboardProperties(searchMouse.getCharger(), "charger").or(filterMKeyboardProperties(searchMouse.getRgb(), "rgb").or(filterMKeyboardProperties(searchMouse.getMaterial(), "material")).or(filterMKeyboardProperties(searchMouse.getExpand(), "expand"))))))));
    }


    private Specification<ProductModal> isPremium(ProductType name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("categoryModal").get("name_category"), name.toString());
    }
}


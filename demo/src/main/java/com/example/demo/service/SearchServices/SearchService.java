package com.example.demo.service.SearchServices;

import com.example.demo.DTO.ProductDTO.ListProductResponseDTO;
import com.example.demo.DTO.ProductDTO.ProductResponseDTO;
import com.example.demo.DTO.ProductDTO.SearchProductDTO.SearchProductLaptopRequestDTO;
import com.example.demo.DTO.ProductDTO.SearchProductDTO.SearchProductMouseRequestDTO;
import com.example.demo.modal.ProductModalPackage.LaptopProperties;
import com.example.demo.modal.ProductModalPackage.MouseProperties;
import com.example.demo.modal.ProductModalPackage.ProductModal;
import com.example.demo.repository.FeedbackRepository.FeedbackRepository;
import com.example.demo.repository.ProductRepository.LaptopPropertiesRepository;
import com.example.demo.repository.ProductRepository.MousePropertiesRepository;
import com.example.demo.repository.ProductRepository.PreviewImageRepository;
import com.example.demo.repository.ProductRepository.ProductRepository;
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
    private final ProductRepository productRepository;
    private final FeedbackRepository feedbackRepository;
    private final PreviewImageRepository previewImageRepository;

    public ListProductResponseDTO sortProduct(String sort, int pageNo, int pageSize, int type) {
        Pageable pageable;
        if (Objects.equals(sort, "descending")) {
            pageable = PageRequest.of(pageNo, pageSize, Sort.by("oldPrice").descending());
        } else {
            pageable = PageRequest.of(pageNo, pageSize, Sort.by("oldPrice").ascending());
        }
        Page<ProductModal> productModals = productRepository.findAll(isPremium(type), pageable);
        ListProductResponseDTO result = new ListProductResponseDTO();
        List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();

        if (Objects.equals(type, 2)) {
            for (ProductModal productModal : productModals) {
                productResponseDTOS.add(TransferMouseProduct.toProductDTO(productModal, feedbackRepository, previewImageRepository));
            }
        }
        if (Objects.equals(type, 1)) {
            for (ProductModal productModal : productModals) {
                productResponseDTOS.add(TransferUtilities.toLaptopProductResponseDTO(productModal, feedbackRepository, previewImageRepository));
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

    //    laptop
    private Specification<LaptopProperties> filterCPU(SearchProductLaptopRequestDTO modal) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.in(root.get("cpu")).value(modal.getCpu()));
    }

    private Specification<LaptopProperties> filterRAM(SearchProductLaptopRequestDTO modal) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.in(root.get("ram")).value(modal.getRam()));
    }

    private Specification<LaptopProperties> filterVGA(SearchProductLaptopRequestDTO modal) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.in(root.get("vga")).value(modal.getVga()));
    }

    private Specification<LaptopProperties> filterSSD(SearchProductLaptopRequestDTO modal) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.in(root.get("ssd")).value(modal.getSsd()));
    }

    private Specification<LaptopProperties> filterCOLOR(SearchProductLaptopRequestDTO modal) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.in(root.get("color")).value(modal.getColor()));
    }

    private Specification<LaptopProperties> filterSCREEN(SearchProductLaptopRequestDTO modal) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.in(root.get("screen")).value(modal.getScreen()));
    }

    private Specification<LaptopProperties> filterSIZE(SearchProductLaptopRequestDTO modal) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.in(root.get("size")).value(modal.getSize()));
    }

    private Specification<LaptopProperties> filterOperatingSystem(SearchProductLaptopRequestDTO modal) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.in(root.get("operatingSystem")).value(modal.getOperatingSystem()));
    }

    private Specification<LaptopProperties> filterTotalLaptop(SearchProductLaptopRequestDTO modal) {
        return Specification.where(filterCPU(modal).or(filterRAM(modal).or(filterVGA(modal).or(filterSSD(modal).or(filterCOLOR(modal)).or(filterOperatingSystem(modal).or(filterSIZE(modal).or(filterSCREEN(modal))))))));
    }

    //    mouse
    private Specification<MouseProperties> filterDpi(List<String> dpi) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.in(root.get("dpi")).value(dpi));
    }

    private Specification<MouseProperties> filterSize(List<String> size) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.in(root.get("dpi")).value(size));
    }

    private Specification<MouseProperties> filterColor(List<String> color) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.in(root.get("color")).value(color));
    }

    private Specification<MouseProperties> filterConnection(List<Boolean> connection) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.in(root.get("connection")).value(connection));
    }

    private Specification<MouseProperties> filterCharger(List<Boolean> charger) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.in(root.get("charger")).value(charger));
    }

    private Specification<MouseProperties> filterRbg(List<Boolean> rbg) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.in(root.get("rgb")).value(rbg));
    }

    private Specification<MouseProperties> filterTotalMouse(SearchProductMouseRequestDTO searchMouse) {
        return Specification.where(filterDpi(searchMouse.getDpi()).or(filterSize(searchMouse.getSize()).or(filterColor(searchMouse.getColor()).or(filterConnection(searchMouse.getConnection()).or(filterCharger(searchMouse.getCharger()).or(filterRbg(searchMouse.getRgb())))))));
    }

    private Specification<ProductModal> isPremium(int id) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("categoryModal").get("id_category"), id);
    }
}


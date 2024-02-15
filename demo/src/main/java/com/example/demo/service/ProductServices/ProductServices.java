package com.example.demo.service.ProductServices;

import com.example.demo.DTO.ProductDTO.FindProductResponseDTO;
import com.example.demo.DTO.ProductDTO.GetImagesBannerResponseDTO;
import com.example.demo.DTO.ProductDTO.ListProductResponseDTO;
import com.example.demo.DTO.ProductDTO.ProductKeyBoardDTO.KeyBoardProductRequestDTO;
import com.example.demo.DTO.ProductDTO.ProductLaptopDTO.LaptopProductRequestDTO;
import com.example.demo.DTO.ProductDTO.ProductMouseDTO.MouseProductRequestDTO;
import com.example.demo.DTO.ProductDTO.ProductResponseDTO;
import com.example.demo.modal.CategoryPackage.CategoryModal;
import com.example.demo.modal.ProducerPackage.ProducerModal;
import com.example.demo.modal.ProductModalPackage.ProductModal;
import com.example.demo.repository.FeedbackRepository.FeedbackRepository;
import com.example.demo.repository.ProductRepository.CategoryRepository;
import com.example.demo.repository.ProductRepository.PreviewImageRepository;
import com.example.demo.repository.ProductRepository.ProducerRepository;
import com.example.demo.repository.ProductRepository.ProductRepository;
import com.example.demo.utilities.TransferKeyBoardProduct;
import com.example.demo.utilities.TransferMouseProduct;
import com.example.demo.utilities.TransferUtilities;
import lombok.RequiredArgsConstructor;
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
public class ProductServices {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final FeedbackRepository feedbackRepository;
    private final PreviewImageRepository previewImageRepository;
    private final ProducerRepository producerRepository;

    //  General ----------------


    public ListProductResponseDTO getAllProduct(int pageNo, int pageSize, int type) {

        Page<ProductModal> listProduct = findAllProduct(pageNo, pageSize, type);

        ListProductResponseDTO result = new ListProductResponseDTO();
        List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();
        if (Objects.equals(type, 2)) {
            for (ProductModal productModal : listProduct) {
                productResponseDTOS.add(TransferMouseProduct.toProductDTO(productModal, feedbackRepository, previewImageRepository));
            }
        }
        if (Objects.equals(type, 3)) {
            for (ProductModal productModal : listProduct) {
                productResponseDTOS.add(TransferKeyBoardProduct.toProductKeyboardDTO(productModal, feedbackRepository, previewImageRepository));
            }
        }
        if (Objects.equals(type, 1)) {
            for (ProductModal productModal : listProduct) {
                productResponseDTOS.add(TransferUtilities.toLaptopProductResponseDTO(productModal, feedbackRepository, previewImageRepository));
            }
        }

        result.setData(productResponseDTOS);
        result.setPageNo(listProduct.getNumber());
        result.setPageSize(listProduct.getSize());
        result.setTotalPages(listProduct.getTotalPages());
        result.setLast(listProduct.isLast());
        result.setTotalElements(listProduct.getTotalElements());

        return result;
    }

    public ProductResponseDTO getProductById(int id, int type) {
        if (productRepository.existsById(id)) {
            ProductModal productModal = productRepository.findProductById(id);

            if (type == 1 && productRepository.findProductById(id).getCategoryModal().getId_category() == type) {
                return TransferUtilities.toLaptopProductResponseDTO(productModal, feedbackRepository, previewImageRepository);
            }
            if (type == 2 && productRepository.findProductById(id).getCategoryModal().getId_category() == type) {
                return TransferMouseProduct.toProductDTO(productModal, feedbackRepository, previewImageRepository);
            }
            if (type == 3 && productRepository.findProductById(id).getCategoryModal().getId_category() == type) {
                return TransferKeyBoardProduct.toProductKeyboardDTO(productModal, feedbackRepository, previewImageRepository);
            }
            return null;
        }
        return null;
    }

    public List<FindProductResponseDTO> getListProductByName(String name) {
        List<ProductModal> productModals = productRepository.findListProductByName(name);
        List<FindProductResponseDTO> findProductResponseDTOS = new ArrayList<>();

        for (ProductModal productModal : productModals) {
            FindProductResponseDTO findProductResponseDTO = new FindProductResponseDTO();
            findProductResponseDTO.setId(productModal.getId_product());
            findProductResponseDTO.setOldPrice(productModal.getOldPrice());
            findProductResponseDTO.setSaleRate(productModal.getSaleRate());
            findProductResponseDTO.setTitle(productModal.getTitle());
            findProductResponseDTO.setImage(productModal.getImage());
            findProductResponseDTO.setCategory(productModal.getCategoryModal().getName_category());
            findProductResponseDTOS.add(findProductResponseDTO);
        }
        return findProductResponseDTOS;
    }

    public List<GetImagesBannerResponseDTO> getListImageBanner(int limit) {
        List<ProductModal> productModals = productRepository.findLimitedRecords(limit);
        List<GetImagesBannerResponseDTO> getImagesBannerResponseDTOS = new ArrayList<>();
        for (ProductModal productModal : productModals) {
            GetImagesBannerResponseDTO getImagesBannerResponseDTO = new GetImagesBannerResponseDTO();
            getImagesBannerResponseDTO.setId(productModal.getId_product());
            getImagesBannerResponseDTO.setImage(productModal.getImage());

            getImagesBannerResponseDTOS.add(getImagesBannerResponseDTO);
        }

        return getImagesBannerResponseDTOS;
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

    public String removeProduct(int idLap) {
        if (productRepository.existsById(idLap)) {
            productRepository.deleteById(idLap);
            return "success";
        }
        return "Not found product";
    }

    //  Laptop ----------------

    public ProductResponseDTO addLaptopProduct(LaptopProductRequestDTO laptopProductRequestDTO) {
        Optional<CategoryModal> categoryModal = categoryRepository.findById(laptopProductRequestDTO.getIdCategory());
        Optional<ProducerModal> producerModal = producerRepository.findById(laptopProductRequestDTO.getIdProducer());
        if (producerModal.isEmpty() || categoryModal.isEmpty()) {
            return null;
        }
        ProductModal product = TransferUtilities.toLaptopProduct(laptopProductRequestDTO, categoryModal.get(), producerModal.get());
        product.setLaptopProperties(TransferUtilities.toLapTopProperties(laptopProductRequestDTO.getProperties(), product));
        ProductModal productModal = productRepository.save(product);
        return TransferUtilities.toLaptopProductResponseDTO(productModal, feedbackRepository, previewImageRepository);
    }

    public String editLaptopProduct(int id, LaptopProductRequestDTO laptopProductRequestDTO) {
        if (productRepository.existsById(id)) {
            ProductModal productModalDB = productRepository.findProductById(id);
            TransferUtilities.toLaptopDB(laptopProductRequestDTO, productModalDB);
            TransferUtilities.toLapTopPropertiesDB(laptopProductRequestDTO.getProperties(), productModalDB.getLaptopProperties());
            productRepository.save(productModalDB);
            return "success";
        }
        return "Not found product";
    }

    // Mouse ----------------

    public ProductResponseDTO addMouseProduct(MouseProductRequestDTO mouseProductRequestDTO) {
        Optional<CategoryModal> categoryModal = categoryRepository.findById(mouseProductRequestDTO.getIdCategory());
        Optional<ProducerModal> producerModal = producerRepository.findById(mouseProductRequestDTO.getIdProducer());
        if (producerModal.isEmpty()) {
            return null;
        }
        if (categoryModal.isEmpty()) {
            return null;
        }
        ProductModal productModal = TransferMouseProduct.toMouseProduct(mouseProductRequestDTO, categoryModal.get(), producerModal.get());
        productModal.setMouseProperties(TransferMouseProduct.toMouseProperties(mouseProductRequestDTO.getProperties(), productModal));
        productRepository.save(productModal);
        ProductModal product = productRepository.save(productModal);
        return TransferMouseProduct.toProductDTO(product, feedbackRepository, previewImageRepository);
    }

    public String editMouseProduct(int id, MouseProductRequestDTO mouseProductRequestDTO) {
        if (!productRepository.existsById(id)) {
            return "Not found product";
        }
        ProductModal prDB = productRepository.findProductById(id);
        TransferMouseProduct.toMouseProductDB(mouseProductRequestDTO, prDB);
        TransferMouseProduct.toMousePropertiesDB(mouseProductRequestDTO.getProperties(), prDB.getMouseProperties());
        productRepository.save(prDB);
        return "success";
    }

    // KeyBoard ----------------
    public ProductResponseDTO addKeyBoardProduct(KeyBoardProductRequestDTO keyBoardProductRequestDTO) {
        Optional<CategoryModal> categoryModal = categoryRepository.findById(keyBoardProductRequestDTO.getIdCategory());
        Optional<ProducerModal> producerModal = producerRepository.findById(keyBoardProductRequestDTO.getIdProducer());
        if (producerModal.isEmpty() || categoryModal.isEmpty()) {
            return null;
        }
        ProductModal productModal = TransferKeyBoardProduct.toKeyBoardProduct(keyBoardProductRequestDTO, categoryModal.get(), producerModal.get());
        productModal.setKeyboardProperties(TransferKeyBoardProduct.toKeyboardProperties(keyBoardProductRequestDTO.getProperties(), productModal));
        productRepository.save(productModal);
        ProductModal product = productRepository.save(productModal);
        return TransferKeyBoardProduct.toProductKeyboardDTO(product, feedbackRepository, previewImageRepository);
    }

    public String editKeyboardProduct(int id, KeyBoardProductRequestDTO keyBoardProductRequestDTO) {
        if (!productRepository.existsById(id)) {
            return "Not found product";
        }
        ProductModal prDB = productRepository.findProductById(id);
        TransferKeyBoardProduct.toKeyboardProductDB(keyBoardProductRequestDTO, prDB);
        TransferKeyBoardProduct.toKeyboardPropertiesDB(keyBoardProductRequestDTO.getProperties(), prDB.getKeyboardProperties());
        productRepository.save(prDB);
        return "success";
    }

    private Specification<ProductModal> isPremium(int id) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("categoryModal").get("id_category"), id);
    }


    private Page<ProductModal> findAllProduct(int pageNo, int pageSize, int type) {
        Pageable pageable;
        if (pageSize > 0) {
            pageable = PageRequest.of(pageNo, pageSize);
        } else {
            pageable = Pageable.unpaged();
        }
        return productRepository.findAll(isPremium(type), pageable);
    }

}

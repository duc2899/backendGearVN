package com.example.demo.service.ProductFeedbackService;

import com.example.demo.DTO.ProductDTO.FeedbackDTO.*;
import com.example.demo.modal.FeedbackPackage.FeedbackModal;
import com.example.demo.modal.ProductModalPackage.ProductModal;
import com.example.demo.modal.UserModalPackage.UserModal;
import com.example.demo.repository.FeedbackRepository.FeedbackRepository;
import com.example.demo.repository.ProductRepository.ProductRepository;
import com.example.demo.repository.UsersRepository.UserRepository;
import com.example.demo.utilities.TransferFeedback;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductFeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public String createFeedback(FeedbackRequestDTO feedbackRequestDTO) {
        if (!userRepository.existsById(feedbackRequestDTO.getIdUser()) || !productRepository.existsById(feedbackRequestDTO.getIdProduct())) {
            return "Invalid user or product";
        }
        if (feedbackRequestDTO.getMessage() == null) {
            return "Message is required";
        }
        if (Objects.equals(feedbackRequestDTO.getMessage(), "")) {
            return "Message is required";
        }
        if (feedbackRequestDTO.getStar() > 5 || feedbackRequestDTO.getStar() < 1) {
            return "Star must be about 1 to 5";
        }
        UserModal userModal = userRepository.findUserByID(feedbackRequestDTO.getIdUser());
        ProductModal productModal = productRepository.findProductById(feedbackRequestDTO.getIdProduct());

        feedbackRepository.save(TransferFeedback.toFeedbackModal(feedbackRequestDTO, productModal, userModal));
        return "success";
    }


    public ListFeedBackResponseDTO getFeedbacksByProduct(int idProduct, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("createdDate").descending());
        ListFeedBackResponseDTO result = new ListFeedBackResponseDTO();
        Page<FeedbackModal> listFeedback = feedbackRepository.findAll(isPremium(idProduct), pageable);

        List<FeedbackResponseDTO> feedbackResponseDTOS = new ArrayList<>();
        for (FeedbackModal feedbackModal : listFeedback) {
            feedbackResponseDTOS.add(TransferFeedback.toFeedbackResponseDTO(feedbackModal));
        }
        result.setData(feedbackResponseDTOS);
        result.setPageNo(listFeedback.getNumber());
        result.setPageSize(listFeedback.getSize());
        result.setTotalPages(listFeedback.getTotalPages());
        result.setLast(listFeedback.isLast());
        result.setTotalElements(listFeedback.getTotalElements());

        return result;
    }

    public String deleteFeedbackProduct(DeleteFeedbackRequestDTO deleteFeedbackRequestDTO) {
        if (!feedbackRepository.existsById(deleteFeedbackRequestDTO.getIdFeedback())) {
            return "Not found feedback product";
        }
        feedbackRepository.deleteById(deleteFeedbackRequestDTO.getIdFeedback());
        if (feedbackRepository.existsById(deleteFeedbackRequestDTO.getIdFeedback())) {
            return "Fault to delete feedback";
        }
        return "success";
    }

    public String editFeedbackProduct(EditFeedbackRequestDTO editFeedbackRequestDTO) {

        if (!feedbackRepository.existsById(editFeedbackRequestDTO.getIdFeedback())) {
            return "Not found feedback";
        }
        if (editFeedbackRequestDTO.getMessage() == null) {
            return "Message is required";
        }
        if (Objects.equals(editFeedbackRequestDTO.getMessage(), "")) {
            return "Message is required";
        }
        if (editFeedbackRequestDTO.getStar() > 5 || editFeedbackRequestDTO.getStar() < 1) {
            return "Star must be about 1 to 5";
        }
        FeedbackModal feedbackModal = feedbackRepository.findFeedbackProductById(editFeedbackRequestDTO.getIdFeedback());
        feedbackModal.setMessage(editFeedbackRequestDTO.getMessage());
        feedbackModal.setStar(editFeedbackRequestDTO.getStar());
        feedbackModal.setIsEdit(true);
        feedbackModal.setCreatedDate(calculateDate());
        feedbackRepository.saveAndFlush(feedbackModal);
        return "success";
    }

    private String calculateDate() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return new SimpleDateFormat("HH:mm:ss dd-MM-yyyy").format(timestamp);
    }

    private Specification<FeedbackModal> isPremium(int id) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("productModal").get("id_product"), id);
    }


}

package com.example.demo.utilities;

import com.example.demo.DTO.ProductDTO.FeedbackDTO.FeedbackRequestDTO;
import com.example.demo.DTO.ProductDTO.FeedbackDTO.FeedbackResponseAdmin;
import com.example.demo.DTO.ProductDTO.FeedbackDTO.FeedbackResponseDTO;
import com.example.demo.modal.FeedbackPackage.FeedbackModal;
import com.example.demo.modal.ProductModalPackage.ProductModal;
import com.example.demo.modal.UserModalPackage.UserModal;

public class TransferFeedback {
    public TransferFeedback() {

    }

    public static FeedbackModal toFeedbackModal(FeedbackRequestDTO feedbackRequestDTO, ProductModal productModal, UserModal userModal) {
        FeedbackModal feedbackModal = new FeedbackModal();
        feedbackModal.setMessage(feedbackRequestDTO.getMessage());
        feedbackModal.setStar(feedbackRequestDTO.getStar());
        feedbackModal.setProductModal(productModal);
        feedbackModal.setUserModal(userModal);
        return feedbackModal;
    }

    public static FeedbackResponseDTO toFeedbackResponseDTO(FeedbackModal feedbackModal) {
        FeedbackResponseDTO result = new FeedbackResponseDTO();
        UserModal user = feedbackModal.getUserModal();
        result.setId(feedbackModal.getId_feedback());
        result.setStar(feedbackModal.getStar());
        result.setCreatedDate(feedbackModal.getCreatedDate());
        result.setMessage(feedbackModal.getMessage());
        result.setName(user.getName());
        result.setIsEdit(feedbackModal.getIsEdit());
        return result;
    }

    public static FeedbackResponseAdmin toFeedbackResponseAdmin(FeedbackModal feedbackModal) {
        FeedbackResponseAdmin result = new FeedbackResponseAdmin();
        result.setId(feedbackModal.getId_feedback());
        result.setImage(feedbackModal.getProductModal().getImage());
        result.setProduct(feedbackModal.getProductModal().getTitle());
        result.setUser(feedbackModal.getUserModal().getEmail());
        result.setMessage(feedbackModal.getMessage());
        result.setCreatedDate(feedbackModal.getCreatedDate());
        result.setStar(feedbackModal.getStar());
        result.setIdProduct(feedbackModal.getProductModal().getId_product());
        return result;
    }
}

package com.example.demo.service.DiscountCodeService;

import com.example.demo.DTO.DiscountCodeDTO.*;
import com.example.demo.modal.DiscountCodePackage.DiscountCodeModal;
import com.example.demo.repository.DiscountCodeRepository.DiscountCodeRepository;
import com.example.demo.utilities.TransferDiscountCode;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class DiscountCodeService {
    private final DiscountCodeRepository discountCodeRepository;

    public DiscountCodeService(DiscountCodeRepository discountCodeRepository) {
        this.discountCodeRepository = discountCodeRepository;
    }

    public GetListDCResponseDTO getDiscountCodeByCode(String code) {
        DiscountCodeModal discountCodeModal = discountCodeRepository.findDiscountCodeByCode(code);
        return TransferDiscountCode.toGetListDCResponseDTO(discountCodeModal);
    }

    public String createDiscountCode(CreateDCRequestDTO createDCRequestDTO) {
        if (!isValid(createDCRequestDTO).equals("valid")) {
            return isValid(createDCRequestDTO);
        }
        if (discountCodeRepository.findDiscountCodeByCodeOptional(createDCRequestDTO.getCode()).isPresent()) {
            return "Discount code already exists";
        }
        if (!createDCRequestDTO.getExpiry().isAfter(LocalDateTime.now())) {
            return "Code expiration invalid";
        }

        discountCodeRepository.save(TransferDiscountCode.toDiscountModal(createDCRequestDTO));
        return "success";
    }

    public List<GetListDCResponseDTO> getListDCResponseDTOS() {
        List<DiscountCodeModal> result = discountCodeRepository.findAll();
        List<GetListDCResponseDTO> getListDCResponseDTOS = new ArrayList<>();
        for (DiscountCodeModal res : result) {
            getListDCResponseDTOS.add(TransferDiscountCode.toGetListDCResponseDTO(res));
        }
        return getListDCResponseDTOS;
    }

    public String editDiscountCode(EditDCRequestDTO editDCRequestDTO) {
        if (!discountCodeRepository.existsById(editDCRequestDTO.getId())) {
            return "Not found discount code";
        }
        if (!isValid(editDCRequestDTO).equals("valid")) {
            return isValid(editDCRequestDTO);
        }
        DiscountCodeModal res = discountCodeRepository.findDiscountCodeById(editDCRequestDTO.getId());
        if (!Objects.equals(res.getCode(), editDCRequestDTO.getCode()) && discountCodeRepository.findDiscountCodeByCodeOptional(editDCRequestDTO.getCode()).isPresent()) {
            return "Discount code already exists";
        }
        if (!editDCRequestDTO.getExpiry().isAfter(LocalDateTime.now())) {
            return "Code expiration invalid";
        }


        res.setExpiry(editDCRequestDTO.getExpiry());
        res.setReduce_price(editDCRequestDTO.getReduce_price());
        res.setCondition_price(editDCRequestDTO.getCondition_price());
        res.setCode(editDCRequestDTO.getCode());

        discountCodeRepository.saveAndFlush(res);
        return "success";
    }

    public String deleteDiscountCode(DeleteDCRequestDTO deleteDCRequestDTO) {
        if (!discountCodeRepository.existsById(deleteDCRequestDTO.getId())) {
            return "Not found discount code";
        }
        discountCodeRepository.deleteById(deleteDCRequestDTO.getId());
        return "success";
    }

    public String checkDiscountCode(CheckDiscountCodeRequestDTO checkDiscountCodeRequestDTO) {
        if (discountCodeRepository.findDiscountCodeByCodeOptional(checkDiscountCodeRequestDTO.getCode()).isEmpty()) {
            return "Mã giảm giá không hợp lệ";
        }
        DiscountCodeModal discountCodeModal = discountCodeRepository.findDiscountCodeByCode(checkDiscountCodeRequestDTO.getCode());
        if (checkDiscountCodeRequestDTO.getPrice() < discountCodeModal.getCondition_price()) {
            return "Giá của sản phẩm không phù hợp";
        }
        if (!discountCodeModal.getExpiry().isAfter(LocalDateTime.now())) {
            return "Mã giảm giá đã hết hạn";
        }
        return "success";
    }

    private String isValid(CreateDCRequestDTO createDCRequestDTO) {
        if (createDCRequestDTO.getCondition_price() <= 0) {
            return "Condition price must be greater than 0";
        }
        if (createDCRequestDTO.getReduce_price() <= 0) {
            return "Reduce price must be greater than 0";
        }
        if (createDCRequestDTO.getExpiry().toString().equals("")) {
            return "Expiry is required";
        }
        if (Objects.equals(createDCRequestDTO.getCode(), "")) {
            return "Code is required";
        }
        return "valid";
    }

    private String isValid(EditDCRequestDTO editDCRequestDTO) {

        if (editDCRequestDTO.getCondition_price() <= 0) {
            return "Condition price must be be greater than 0";
        }
        if (editDCRequestDTO.getReduce_price() <= 0) {
            return "Reduce price must be be greater than 0";
        }
        if (editDCRequestDTO.getExpiry().toString().equals("")) {
            return "Expiry is required";
        }
        if (Objects.equals(editDCRequestDTO.getCode(), "")) {
            return "Code is required";
        }
        return "valid";
    }
}

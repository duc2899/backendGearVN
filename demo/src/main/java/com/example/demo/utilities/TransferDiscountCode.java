package com.example.demo.utilities;

import com.example.demo.DTO.DiscountCodeDTO.CreateDCRequestDTO;
import com.example.demo.DTO.DiscountCodeDTO.GetListDCResponseDTO;
import com.example.demo.modal.DiscountCodePackage.DiscountCodeModal;
import org.springframework.beans.BeanUtils;

public class TransferDiscountCode {
    public TransferDiscountCode() {

    }

    public static DiscountCodeModal toDiscountModal(CreateDCRequestDTO createDCRequestDTO) {
        DiscountCodeModal discountCodeModal = new DiscountCodeModal();
        BeanUtils.copyProperties(createDCRequestDTO, discountCodeModal);
        return discountCodeModal;
    }

    public static GetListDCResponseDTO toGetListDCResponseDTO(DiscountCodeModal discountCodeModal) {
        GetListDCResponseDTO getListDCResponseDTO = new GetListDCResponseDTO();
        BeanUtils.copyProperties(discountCodeModal, getListDCResponseDTO);
        return getListDCResponseDTO;
    }
}

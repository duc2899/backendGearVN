package com.example.demo.utilities;

import com.example.demo.DTO.BillDTO.BillResponseDTO;
import com.example.demo.DTO.BillDTO.ItemResponseBillDTO;
import com.example.demo.modal.BillModalPackage.BillModal;
import com.example.demo.modal.ProductModalPackage.ProductModal;

import java.util.ArrayList;
import java.util.List;

public class TransferBill {
    public static BillResponseDTO toBillResponseDTO(BillModal billModal, List<ProductModal> productModals, List<Integer> amount) {
        BillResponseDTO billResponseDTO = new BillResponseDTO();
        billResponseDTO.setId(billModal.getIdBill());
        billResponseDTO.setAddress(billModal.getAddress());
        billResponseDTO.setIsPay(billModal.getIsPay());
        billResponseDTO.setName(billModal.getName());
        billResponseDTO.setPaymentType(billModal.getPaymentType());
        billResponseDTO.setPhoneNumber(billModal.getPhoneNumber());
        billResponseDTO.setPriceDelivery(billModal.getPriceDelivery());
        billResponseDTO.setCreatedDate(billModal.getCreatedDate());
        billResponseDTO.setDiscountCode(billModal.getApplyDiscountCode());
        billResponseDTO.setTotalPrice(billModal.getTotalPrice());
        billResponseDTO.setStatusBill(billModal.getStatusOrder());
        billResponseDTO.setIsCancelOrder(billModal.getIsCancelOrder());
        List<ItemResponseBillDTO> itemResponseBillDTOS = new ArrayList<>();
        if (productModals != null && productModals.size() > 0) {
            int index = 0;
            for (ProductModal productModal : productModals) {
                itemResponseBillDTOS.add(ItemResponseBillDTO.builder()
                        .id(productModal.getId_product())
                        .price(productModal.getOldPrice())
                        .name(productModal.getTitle())
                        .image(productModal.getImage())
                        .saleRate(productModal.getSaleRate())
                        .amount(amount.get(index++))
                        .build());
            }
        }
        billResponseDTO.setProducts(itemResponseBillDTOS);
        return billResponseDTO;
    }
}

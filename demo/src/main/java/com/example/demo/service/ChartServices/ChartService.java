package com.example.demo.service.ChartServices;

import com.example.demo.DTO.ChartDTO.RevenueMonthListResponseDTO;
import com.example.demo.modal.BillModalPackage.BillModal;
import com.example.demo.repository.BillRepository.BillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChartService {
    private final BillRepository billRepository;

    public RevenueMonthListResponseDTO getRevenueMonth() {
        RevenueMonthListResponseDTO result = new RevenueMonthListResponseDTO();
        List<Double> list = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            List<BillModal> billModalList = billRepository.findBillByMonth(i);
            Double revenueMonth = 0.0;
            if (billModalList.size() > 0) {
                for (BillModal billModal : billModalList) {
                    if (billModal.getIsPay()) {
                        revenueMonth += calculateRevenueMonth(billModal);
                    }
                }
                list.add(revenueMonth);
            } else {
                list.add(0.0);
            }
        }
        result.setListRevenue(list);
        return result;
    }

    private double calculateRevenueMonth(BillModal billModal) {
        return billModal.getPriceTemporary() + billModal.getPriceDelivery() - billModal.getPriceDiscount();
    }
}

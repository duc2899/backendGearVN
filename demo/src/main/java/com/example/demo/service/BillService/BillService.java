package com.example.demo.service.BillService;

import com.example.demo.DTO.AcccountDTO.CartDTO.CartRequestDTO;
import com.example.demo.DTO.BillDTO.*;
import com.example.demo.modal.BillModalPackage.BillModal;
import com.example.demo.modal.BillModalPackage.OrderModal;
import com.example.demo.modal.DiscountCodePackage.DiscountCodeModal;
import com.example.demo.modal.ProductModalPackage.ProductModal;
import com.example.demo.modal.UserModalPackage.UserModal;
import com.example.demo.repository.BillRepository.BillRepository;
import com.example.demo.repository.BillRepository.OrderRepository;
import com.example.demo.repository.DiscountCodeRepository.DiscountCodeRepository;
import com.example.demo.repository.ProductRepository.ProductRepository;
import com.example.demo.repository.UsersRepository.UserRepository;
import com.example.demo.utilities.TransferBill;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BillService {
    private final BillRepository billRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final DiscountCodeRepository discountCodeRepository;

    private final ProductRepository productRepository;

    public BillService(BillRepository billRepository, OrderRepository orderRepository, UserRepository userRepository, DiscountCodeRepository discountCodeRepository, ProductRepository productRepository) {
        this.billRepository = billRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.discountCodeRepository = discountCodeRepository;
        this.productRepository = productRepository;
    }

    //    User
    public List<BillResponseDTO> getBillByIdUserAndIdBill(FindBillByIdUserAndIdBillRequestDTO findBillByIdUserAndIdBillRequestDTO) {
        List<BillModal> billModals = billRepository.findBillsByIdUserAndIdBill(findBillByIdUserAndIdBillRequestDTO.getIdUser(), findBillByIdUserAndIdBillRequestDTO.getIdBill());
        List<BillResponseDTO> result = new ArrayList<>();
        for (BillModal billModal : billModals) {
            List<ProductModal> productModals = new ArrayList<>();
            List<Integer> amount = new ArrayList<>();
            if (billModal.getOrderProduct() != null && billModal.getOrderProduct().size() > 0) {
                Set<OrderModal> orderModals = billModal.getOrderProduct();
                for (OrderModal orderModal : orderModals) {
                    productModals.addAll(orderModal.getProductModals());
                    amount.add(orderModal.getAmount());
                }
            }
            result.add(TransferBill.toBillResponseDTO(billModal, productModals, amount));
        }
        return result;
    }

    public List<BillResponseDTO> getListBillByUser(GetBillsByUserRequestDTO getBillsByUserRequestDTO) {
        List<BillModal> billModals = billRepository.findBillsByIdUser(getBillsByUserRequestDTO.getIdUser());
        List<BillResponseDTO> result = new ArrayList<>();
        for (BillModal billModal : billModals) {
            List<ProductModal> productModals = new ArrayList<>();
            List<Integer> amount = new ArrayList<>();
            if (billModal.getOrderProduct() != null && billModal.getOrderProduct().size() > 0) {
                Set<OrderModal> orderModals = billModal.getOrderProduct();
                for (OrderModal orderModal : orderModals) {
                    productModals.addAll(orderModal.getProductModals());
                    amount.add(orderModal.getAmount());
                }
            }
            result.add(TransferBill.toBillResponseDTO(billModal, productModals, amount));
        }
        return result;
    }

    public String createBill(BillRequestDTO billReq) {

        UserModal user = userRepository.findUserByID(billReq.getIdUser());
        BillModal billModal = new BillModal();

        billModal.setUserBill(user);
        billModal.setAddress(billReq.getAddress());
        billModal.setName(billReq.getName());
        billModal.setPhoneNumber(billReq.getPhoneNumber());
        billModal.setIsPay(billReq.getIsPay());
        billModal.setStatusOrder(1);
        billModal.setPaymentType(billReq.getPaymentType());
        billModal.setPriceDelivery(billReq.getPriceDelivery());
        billModal.setTotalPrice(calculatorTotalPay(billReq));
        billModal.setIsCancelOrder(false);
        Optional<DiscountCodeModal> discountCodeModal = discountCodeRepository.findDiscountCodeByCodeOptional(billReq.getDiscountCode());
        if (discountCodeModal.isPresent()) {
            billModal.setApplyDiscountCode(billReq.getDiscountCode());
        } else {
            billModal.setApplyDiscountCode("");
        }
        List<CartRequestDTO> items = billReq.getProducts();
        Set<OrderModal> orderModals = new HashSet<>();

        for (CartRequestDTO item : items) {
            ProductModal productModal = productRepository.findProductById(item.getId_product());
            OrderModal orderModal = new OrderModal();

            orderModal.setAmount(item.getAmount());
            orderModal.setOrderBill(billModal);
            productModal.getOrderMeal().add(orderModal);
            orderModal.getProductModals().add(productModal);
            orderModals.add(orderModal);
        }
        billModal.setOrderProduct(orderModals);
        billRepository.save(billModal);
        return "success";
    }

    //    Admin
    public List<BillResponseDTO> getListBills() {
        List<BillModal> billModals = billRepository.findAll();
        List<BillResponseDTO> result = new ArrayList<>();
        for (BillModal billModal : billModals) {
            List<ProductModal> productModals = new ArrayList<>();
            List<Integer> amount = new ArrayList<>();
            if (billModal.getOrderProduct() != null && billModal.getOrderProduct().size() > 0) {
                Set<OrderModal> orderModals = billModal.getOrderProduct();
                for (OrderModal orderModal : orderModals) {
                    productModals.addAll(orderModal.getProductModals());
                    amount.add(orderModal.getAmount());
                }
            }
            result.add(TransferBill.toBillResponseDTO(billModal, productModals, amount));
        }
        return result;
    }

    public String editStatusBill(EditStatusBillRequestDTO editStatusBillRequestDTO) {
        if (editStatusBillRequestDTO.getStatusBill() > 4 || editStatusBillRequestDTO.getStatusBill() < 1) {
            return "Invalid status bill";
        }
        if (billRepository.findById(editStatusBillRequestDTO.getIdBill()).isEmpty()) {
            return "Not found bill";
        }
        billRepository.updateStatusBill(editStatusBillRequestDTO.getStatusBill(), editStatusBillRequestDTO.getIdBill());
        return "success";
    }

    public String paymentProcessing(EditPaymentBillRequestDTO editPaymentBillRequestDTO) {
        if (billRepository.findById(editPaymentBillRequestDTO.getIdBill()).isEmpty()) {
            return "Not found bill";
        }
        billRepository.updateIsPay(editPaymentBillRequestDTO.isPay(), editPaymentBillRequestDTO.getIdBill());
        return "success";
    }

    private double calculatorTotalPay(BillRequestDTO billRequestDTO) {
        double totalResult = 0;
        for (CartRequestDTO o : billRequestDTO.getProducts()) {
            Optional<ProductModal> productModal = productRepository.findById(o.getId_product());
            if (productModal.isPresent()) {
                double pricePro = productModal.get().getOldPrice() * productModal.get().getSaleRate();
                totalResult += o.getAmount() * pricePro;
            }
        }
        if (!Objects.equals(billRequestDTO.getDiscountCode(), "")) {
            Optional<DiscountCodeModal> discountCodeModal = discountCodeRepository.findDiscountCodeByCodeOptional(billRequestDTO.getDiscountCode());
            if (discountCodeModal.isPresent()) {
                return totalResult + billRequestDTO.getPriceDelivery() - discountCodeModal.get().getReduce_price();
            }
        }
        return totalResult + billRequestDTO.getPriceDelivery();
    }
}

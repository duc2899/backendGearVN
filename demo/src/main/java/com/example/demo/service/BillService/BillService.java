package com.example.demo.service.BillService;

import com.example.demo.DTO.AcccountDTO.CartDTO.CartRequestDTO;
import com.example.demo.DTO.BillDTO.*;
import com.example.demo.modal.BillModalPackage.BillModal;
import com.example.demo.modal.BillModalPackage.OrderModal;
import com.example.demo.modal.CartPackage.CartModal;
import com.example.demo.modal.DiscountCodePackage.DiscountCodeModal;
import com.example.demo.modal.ProductModalPackage.ProductModal;
import com.example.demo.modal.UserModalPackage.UserModal;
import com.example.demo.repository.BillRepository.BillRepository;
import com.example.demo.repository.BillRepository.OrderRepository;
import com.example.demo.repository.CartRepository.CartRepository;
import com.example.demo.repository.DiscountCodeRepository.DiscountCodeRepository;
import com.example.demo.repository.ProductRepository.ProductRepository;
import com.example.demo.repository.UsersRepository.UserRepository;
import com.example.demo.utilities.ConvertMoneyVietNam;
import com.example.demo.utilities.TransferBill;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BillService {
    private final BillRepository billRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final DiscountCodeRepository discountCodeRepository;

    private final ProductRepository productRepository;
    @Value("${spring.mail.username}")
    private String formEmail;
    @Autowired
    private JavaMailSender javaMailSender;


    public BillService(BillRepository billRepository, OrderRepository orderRepository, UserRepository userRepository, CartRepository cartRepository, DiscountCodeRepository discountCodeRepository, ProductRepository productRepository) {
        this.billRepository = billRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.discountCodeRepository = discountCodeRepository;
        this.productRepository = productRepository;
    }

    //    User
    public List<BillResponseDTO> getBillByIdUserAndIdBill(FindBillByIdUserAndIdBillRequestDTO findBillByIdUserAndIdBillRequestDTO) {
        List<BillModal> billModals = billRepository.findBillsByIdUserAndIdBill(findBillByIdUserAndIdBillRequestDTO.getIdUser(), findBillByIdUserAndIdBillRequestDTO.getIdBill());
        List<BillResponseDTO> result = new ArrayList<>();
        for (BillModal billModal : billModals) {
            UserModal userModal = userRepository.findUserByID(billModal.getUserBill().getId_user());
            List<ProductModal> productModals = new ArrayList<>();
            List<Integer> amount = new ArrayList<>();
            if (billModal.getOrderProduct() != null && billModal.getOrderProduct().size() > 0) {
                Set<OrderModal> orderModals = billModal.getOrderProduct();
                for (OrderModal orderModal : orderModals) {
                    productModals.addAll(orderModal.getProductModals());
                    amount.add(orderModal.getAmount());
                }
            }
            result.add(TransferBill.toBillResponseDTO(billModal, productModals, amount, userModal));
        }
        return result;
    }

    public List<BillResponseDTO> getListBillByUser(int idUser) {
        List<BillModal> billModals = billRepository.findBillsByIdUser(idUser);
        List<BillResponseDTO> result = new ArrayList<>();
        for (BillModal billModal : billModals) {
            UserModal userModal = userRepository.findUserByID(billModal.getUserBill().getId_user());
            List<ProductModal> productModals = new ArrayList<>();
            List<Integer> amount = new ArrayList<>();
            if (billModal.getOrderProduct() != null && billModal.getOrderProduct().size() > 0) {
                Set<OrderModal> orderModals = billModal.getOrderProduct();
                for (OrderModal orderModal : orderModals) {
                    productModals.addAll(orderModal.getProductModals());
                    amount.add(orderModal.getAmount());
                }
            }
            result.add(TransferBill.toBillResponseDTO(billModal, productModals, amount, userModal));
        }
        return result;
    }

    public BillResponseDTO createBill(BillRequestDTO billReq) {
        if (!userRepository.existsById(billReq.getIdUser())) {
            return null;
        }
        UserModal user = userRepository.findUserByID(billReq.getIdUser());
        BillModal billModal = new BillModal();

        billModal.setUserBill(user);
        billModal.setAddress(billReq.getAddress());
        billModal.setName(billReq.getName());
        billModal.setPhoneNumber(billReq.getPhoneNumber());
        billModal.setIsPay(billReq.isPay());
        billModal.setStatusOrder(1);
        billModal.setPaymentType(billReq.getPaymentType());
        billModal.setPriceDelivery(billReq.getPriceDelivery());
        billModal.setPriceTemporary(calculatorTemporaryPrice(billReq));
        billModal.setIsCancelOrder(false);
        billModal.setSexType(billReq.getSex());
        billModal.setNote(billReq.getNote());

        Optional<DiscountCodeModal> discountCodeModal = discountCodeRepository.findDiscountCodeByCodeOptional(billReq.getDiscountCode());
        if (discountCodeModal.isPresent()) {
            billModal.setPriceDiscount(discountCodeModal.get().getReduce_price());
        } else {
            billModal.setPriceDiscount(0);
        }

        List<CartRequestDTO> items = billReq.getProducts();
        Set<OrderModal> orderModals = new HashSet<>();


        for (CartRequestDTO item : items) {
            if (productRepository.existsById(item.getId_product())) {
                ProductModal productModal = productRepository.findProductById(item.getId_product());
                OrderModal orderModal = new OrderModal();

                orderModal.setAmount(item.getAmount());
                orderModal.setOrderBill(billModal);
                productModal.getOrderMeal().add(orderModal);
                orderModal.getProductModals().add(productModal);
                orderModals.add(orderModal);
            } else {
                return null;
            }

        }
        billModal.setOrderProduct(orderModals);
        BillModal billDB = billRepository.save(billModal);

        List<ProductModal> productModals = new ArrayList<>();
        List<Integer> amount = new ArrayList<>();
        Set<OrderModal> orderProduct = billDB.getOrderProduct();

        for (OrderModal orderModal : orderProduct) {
            productModals.addAll(orderModal.getProductModals());
            amount.add(orderModal.getAmount());
        }

        if (handelDeleteCart(productModals, billReq.getIdUser()).equals("fault")) {
            return null;
        }
        sendEmail(billDB);
        handelCalculatorQuantity(productModals, amount);
        return TransferBill.toBillResponseDTO(billDB, productModals, amount, user);
    }


    //    Admin
    public List<BillResponseDTO> getListBills() {
        List<BillModal> billModals = billRepository.findAll();
        List<BillResponseDTO> result = new ArrayList<>();
        for (BillModal billModal : billModals) {
            UserModal userModal = userRepository.findUserByID(billModal.getUserBill().getId_user());
            List<ProductModal> productModals = new ArrayList<>();
            List<Integer> amount = new ArrayList<>();
            if (billModal.getOrderProduct() != null && billModal.getOrderProduct().size() > 0) {
                Set<OrderModal> orderModals = billModal.getOrderProduct();
                for (OrderModal orderModal : orderModals) {
                    productModals.addAll(orderModal.getProductModals());
                    amount.add(orderModal.getAmount());
                }
            }
            result.add(TransferBill.toBillResponseDTO(billModal, productModals, amount, userModal));
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

    public String cancelBill(int idBill) {
        if (!billRepository.existsById(idBill)) {
            return "Not found bill";
        }
        BillModal billModal = billRepository.findBillsByIdBill(idBill);
        billModal.setIsCancelOrder(true);
        billRepository.save(billModal);
        return "success";
    }

    public String paymentProcessing(EditPaymentBillRequestDTO editPaymentBillRequestDTO) {
        if (billRepository.findById(editPaymentBillRequestDTO.getIdBill()).isEmpty()) {
            return "Not found bill";
        }
        billRepository.updateIsPay(editPaymentBillRequestDTO.isPay(), editPaymentBillRequestDTO.getIdBill());
        return "success";
    }

    private double calculatorTemporaryPrice(BillRequestDTO billRequestDTO) {
        double totalResult = 0;
        for (CartRequestDTO o : billRequestDTO.getProducts()) {
            Optional<ProductModal> productModal = productRepository.findById(o.getId_product());
            if (productModal.isPresent()) {
                double percent = (100 - productModal.get().getSaleRate() * 100) / 100;
                double pricePro = productModal.get().getOldPrice() * percent;
                totalResult += o.getAmount() * pricePro;
            }
        }
        return totalResult;
    }

    private String handelDeleteCart(List<ProductModal> listProduct, int idUser) {
        for (ProductModal productModal : listProduct) {
            CartModal carDB = cartRepository.findCartByItemAndUser(productModal.getId_product(), idUser);
            if (carDB == null) {
                return "fault";
            }
            cartRepository.delete(carDB);
        }
        return "success";
    }

    private void handelCalculatorQuantity(List<ProductModal> listProduct, List<Integer> amount) {
        int index = 0;
        for (ProductModal productModal : listProduct) {
            ProductModal proDB = productRepository.findProductById(productModal.getId_product());
            proDB.setQuantity(proDB.getQuantity() - amount.get(index));
            productRepository.save(proDB);
            index++;
        }
    }

    private String sendEmail(BillModal billModal) {
        Set<OrderModal> orderModals = billModal.getOrderProduct();
        ConvertMoneyVietNam convertMoneyVietNam = new ConvertMoneyVietNam();
        try {
            MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, false);
            mimeMessageHelper.setFrom(formEmail);
            StringBuilder body = new StringBuilder("<html><body>" +
                    "<div>" +
                    "<p>Tên khách hàng: <b>" + billModal.getName() + "</b></p>" +
                    "<p>Thời gian tạo đơn: <b>" + billModal.getCreatedDate() + "</b></p>" +
                    "<p>Số điện thoại: <b>" + billModal.getPhoneNumber() + "</b></p>" +
                    "<p>Địa chỉ nhận hàng: <b>" + billModal.getAddress() + "</b></p>" +
                    "</div>" +
                    "<div>" +
                    "<table border='1'>" +
                    "<tr><th>Tên sản phẩm</th><th>Số lượng</th><th>Giá</th></tr>"
            );

            for (OrderModal orderModal : orderModals) {
                Set<ProductModal> productModals = orderModal.getProductModals();
                for (ProductModal productModal : productModals) {
                    double percent = (100 - productModal.getSaleRate() * 100) / 100;
                    double pricePro = productModal.getOldPrice() * percent;
                    body.append("<tr><td>").append(productModal.getTitle()).append("</td><td>").append(orderModal.getAmount()).append("</td><td>").append(convertMoneyVietNam.formatMoney(pricePro)).append("</td></tr>");
                }
            }
            body.append("</div>");
            body.append("<div style='margin-top: 10px;'>").append("Phí vận chuyển: ").append(convertMoneyVietNam.formatMoney(billModal.getPriceDelivery())).append("</div>");
            body.append("<div>").append("Mã giảm giá: -").append(convertMoneyVietNam.formatMoney(billModal.getPriceDiscount())).append("</div>");
            body.append("<b style='font-size: 18px;'>").append("Tổng hóa đơn: ").append(convertMoneyVietNam.formatMoney(billModal.getPriceTemporary() + billModal.getPriceDelivery() - billModal.getPriceDiscount())).append("</b>");
            body.append("</body></html>");
            mimeMailMessage.setContent(body.toString(), "text/html; charset=utf-8");
            mimeMailMessage.setSubject("Hóa đơn thanh toán");
            mimeMessageHelper.setTo(billModal.getUserBill().getEmail());
            javaMailSender.send(mimeMailMessage);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        return "send mail";
    }
}

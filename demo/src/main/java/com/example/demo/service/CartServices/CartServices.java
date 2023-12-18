package com.example.demo.service.CartServices;

import com.example.demo.DTO.AcccountDTO.CartRequestDTO;
import com.example.demo.DTO.AcccountDTO.CartResponseDTO;
import com.example.demo.modal.CartPackage.CartModal;
import com.example.demo.repository.CartRepository.CartRepository;
import com.example.demo.repository.ProductRepository.ProductRepository;
import com.example.demo.repository.UsersRepository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CartServices {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;

    public List<CartResponseDTO> getCartUser(int idUser){
          List<CartResponseDTO> cartResponseDTOList = new ArrayList<>();
          List<CartModal> cartModalList = cartRepository.findCartByUser(idUser);
          for (CartModal cartModal: cartModalList){

              CartResponseDTO cartResponseDTO = new CartResponseDTO();
              cartResponseDTO.setId(cartModal.getId_cart());
              cartResponseDTO.setAmount(cartModal.getAmount());
              cartResponseDTO.setTitle(cartModal.getProductModal().getTitle());
              cartResponseDTO.setOldPrice(cartModal.getProductModal().getOldPrice());
              cartResponseDTO.setSaleRate(cartModal.getProductModal().getSaleRate());
              cartResponseDTO.setImage(cartModal.getProductModal().getImage());
              cartResponseDTO.setName_category(cartModal.getProductModal().getCategoryModal().getName_category());

              cartResponseDTOList.add(cartResponseDTO);
          }
          return cartResponseDTOList;
    }

    public String addToCart (CartRequestDTO cartRequestDTO){
        if(userRepository.findById(cartRequestDTO.getIdUser()).isEmpty()){
            return "User not exits to add product";
        }
        if(cartRequestDTO.getAmount() < 0){
            return "Amount must be bigger than 0";
        }
        if(productRepository.findById(cartRequestDTO.getId_product()).isEmpty()){
            return "Product not exits to add product";
        }

        CartModal carDB = cartRepository.findCartByItemAndUser(cartRequestDTO.getId_product(), cartRequestDTO.getIdUser());
        if ( carDB == null){
            CartModal newCart = new CartModal();
            newCart.setUserCartModal(userRepository.findUserByID(cartRequestDTO.getIdUser()));
            newCart.setAmount(cartRequestDTO.getAmount());
            newCart.setProductModal(productRepository.findProductById(cartRequestDTO.getId_product()));
            cartRepository.save(newCart);
        }else {
            carDB.setAmount(carDB.getAmount() + cartRequestDTO.getAmount());
            cartRepository.save(carDB);
        }
        return "success";
    }

}

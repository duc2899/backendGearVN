package com.example.demo.service.FavoriteProductServices;

import com.example.demo.DTO.ProductDTO.FavoriteProductDTO.CreateFavoriteProductRequestDTO;
import com.example.demo.DTO.ProductDTO.FavoriteProductDTO.FavoriteProductResponseDTO;
import com.example.demo.modal.ProductModalPackage.FavoriteProductModal;
import com.example.demo.repository.ProductRepository.FavoriteProductRepository;
import com.example.demo.repository.ProductRepository.ProductRepository;
import com.example.demo.repository.UsersRepository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteProductServices {
    private final FavoriteProductRepository favoriteProductRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public List<FavoriteProductResponseDTO> getListFavoriteProductByUser(int idUser) {
        List<FavoriteProductResponseDTO> result = new ArrayList<>();

        List<FavoriteProductModal> favoriteProductModalList = favoriteProductRepository.findFavoriteProductByIdUser(idUser);
        for (FavoriteProductModal favoriteProductModal : favoriteProductModalList) {
            FavoriteProductResponseDTO favoriteProductResponseDTO = new FavoriteProductResponseDTO();
            favoriteProductResponseDTO.setId(favoriteProductModal.getId_favorite_product());
            favoriteProductResponseDTO.setTitle(favoriteProductModal.getProductModal().getTitle());
            favoriteProductResponseDTO.setImage(favoriteProductModal.getProductModal().getImage());
            favoriteProductResponseDTO.setOldPrice(favoriteProductModal.getProductModal().getOldPrice());
            favoriteProductResponseDTO.setSaleRate(favoriteProductModal.getProductModal().getSaleRate());
            favoriteProductResponseDTO.setIdProduct(favoriteProductModal.getProductModal().getId_product());
            favoriteProductResponseDTO.setType(favoriteProductModal.getProductModal().getCategoryModal().getName_category());
            result.add(favoriteProductResponseDTO);
        }
        return result;
    }

    public String crateFavoriteProduct(CreateFavoriteProductRequestDTO createFavoriteProductRequestDTO) {
        if (!userRepository.existsById(createFavoriteProductRequestDTO.getIdUser())) {
            return "User not found";
        }
        if (!productRepository.existsById(createFavoriteProductRequestDTO.getIdProduct())) {
            return "Product not found";
        }
        if (favoriteProductRepository.findFavoriteProductByIdUserAndIdProduct(createFavoriteProductRequestDTO.getIdUser(), createFavoriteProductRequestDTO.getIdProduct()).isPresent()) {
            return "Favorite product is exited";
        }
        FavoriteProductModal favoriteProductModal = new FavoriteProductModal();
        favoriteProductModal.setProductModal(productRepository.findProductById(createFavoriteProductRequestDTO.getIdProduct()));
        favoriteProductModal.setUserModal(userRepository.findUserByID(createFavoriteProductRequestDTO.getIdUser()));
        favoriteProductRepository.save(favoriteProductModal);
        return "success";
    }

    public String deleteFavoriteProduct(int id) {
        if (!favoriteProductRepository.existsById(id)) {
            return "Not found favorite product";
        }
        favoriteProductRepository.delete(favoriteProductRepository.findById(id).get());
        return "success";
    }
}

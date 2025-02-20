package org.mercatto.mercatto_backend.mapper;

import org.mercatto.mercatto_backend.dto.response.CartItemResponse;
import org.mercatto.mercatto_backend.dto.response.CartResponse;
import org.mercatto.mercatto_backend.model.CartItemModel;
import org.mercatto.mercatto_backend.model.CartModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartMapper {

//    private final UserRepository userRepository;
//    private final ProductRepository productRepository;
//
//    public CartMapper(UserRepository userRepository, ProductRepository productRepository) {
//        this.userRepository = userRepository;
//        this.productRepository = productRepository;
//    }

//    public CartModel toEntity(CartRequest request) {
//        CartModel cart = new CartModel();
//
//        UserModel user = userRepository.findById(request.getUserId())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//        cart.setUser(user);
//
//        for (CartItemRequest item : request.getItems()) {
//            ProductModel product = productRepository.findById(item.getProductId())
//                    .orElseThrow(() -> new RuntimeException("Product not found"));
//
//            CartItemModel cartItem = new CartItemModel();
//            cartItem.setCart(cart);
//            cartItem.setProduct(product);
//            cartItem.setQuantity(item.getQuantity());
//            cart.getItems().add(cartItem);
//        }
//        return cart;
//    }

    public CartResponse toResponse(CartModel cart) {
        CartResponse response = new CartResponse();
        response.setId(cart.getId());

        List<CartItemResponse> items = cart.getItems().stream()
                .map(this::toCartItemResponse)
                .collect(Collectors.toList());
        response.setItems(items);

        return response;
    }

    private CartItemResponse toCartItemResponse(CartItemModel item) {
        CartItemResponse response = new CartItemResponse();
        response.setProductId(item.getProduct().getId());
        response.setProductName(item.getProduct().getName());
        response.setPrice(item.getProduct().getPrice());
        response.setQuantity(item.getQuantity());
        return response;
    }
}
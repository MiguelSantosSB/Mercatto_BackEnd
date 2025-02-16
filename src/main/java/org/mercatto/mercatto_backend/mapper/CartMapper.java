package org.mercatto.mercatto_backend.mapper;

import org.mercatto.mercatto_backend.dto.request.CartItemRequest;
import org.mercatto.mercatto_backend.dto.request.CartRequest;
import org.mercatto.mercatto_backend.dto.response.CartItemResponse;
import org.mercatto.mercatto_backend.dto.response.CartResponse;
import org.mercatto.mercatto_backend.model.CartItemModel;
import org.mercatto.mercatto_backend.model.CartModel;
import org.mercatto.mercatto_backend.model.ProductModel;
import org.mercatto.mercatto_backend.model.UserModel;
import org.mercatto.mercatto_backend.repositories.ProductRepository;
import org.mercatto.mercatto_backend.repositories.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartMapper {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public CartMapper(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public CartModel toEntity(CartRequest request) {
        CartModel cart = new CartModel();

        UserModel user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        cart.setUser(user);

        for (CartItemRequest item : request.getItems()) {
            ProductModel product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            CartItemModel cartItem = new CartItemModel();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(item.getQuantity());
            cart.addItem(cartItem);
        }
        return cart;
    }

    public CartResponse toResponse(CartModel cart) {
        CartResponse response = new CartResponse();
        response.setId(cart.getId());
        response.setUserId(cart.getUser().getId());

        List<CartItemResponse> itemResponses = cart.getItems().stream()
                .map(this::toCartItemREsponse)
                .collect(Collectors.toList());
        response.setItems(itemResponses);

        return response;
    }

    private CartItemResponse toCartItemREsponse(CartItemModel item) {
        CartItemResponse response = new CartItemResponse();
        response.setProductId(item.getProduct().getId());
        response.setProductName(item.getProduct().getName());
        response.setQuantity(item.getQuantity());
        response.setPrice(item.getProduct().getPrice());
        return response;
    }
}
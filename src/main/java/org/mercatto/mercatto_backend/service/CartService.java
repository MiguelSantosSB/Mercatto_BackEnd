package org.mercatto.mercatto_backend.service;

import org.mercatto.mercatto_backend.dto.request.CartRequest;
import org.mercatto.mercatto_backend.dto.request.OrderRequest;
import org.mercatto.mercatto_backend.dto.response.CartResponse;
import org.mercatto.mercatto_backend.dto.response.OrderResponse;
import org.mercatto.mercatto_backend.model.UserModel;

public interface CartService {

    CartResponse addItemToCart(CartRequest cartRequest);
    CartResponse findById(Long id);
    void removeItemFromCart(Long id, Long productId);
    OrderResponse checkoutCart(UserModel user, OrderRequest request);
}

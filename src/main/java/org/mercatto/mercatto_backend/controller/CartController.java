package org.mercatto.mercatto_backend.controller;

import org.mercatto.mercatto_backend.dto.request.CartRequest;
import org.mercatto.mercatto_backend.dto.request.OrderRequest;
import org.mercatto.mercatto_backend.dto.response.CartResponse;
import org.mercatto.mercatto_backend.dto.response.OrderResponse;
import org.mercatto.mercatto_backend.model.UserModel;
import org.mercatto.mercatto_backend.repositories.UserRepository;
import org.mercatto.mercatto_backend.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService service;
    private final UserRepository userRepository;

    public CartController(CartService cartService, UserRepository userRepository) {
        this.service = cartService;
        this.userRepository = userRepository;
    }

    @PostMapping("/add")
    public ResponseEntity<CartResponse> addItem(@RequestBody CartRequest request) {
        CartResponse response = service.addItemToCart(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public CartResponse findByUser(@RequestParam Long userId) {
        UserModel user = userRepository.findById(userId)
                        .orElseThrow(() -> new RuntimeException("User not found"));
        return service.findById(user.getId());
    }

    @PostMapping("/remove")
    public void removeItem(@RequestParam Long productId,@RequestBody CartRequest request) {
        UserModel user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        service.removeItemFromCart(user.getId(), productId);
    }

    @PostMapping("/checkout")
    public OrderResponse checkoutCart(@RequestParam Long userId, @RequestBody OrderRequest request) {
        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return service.checkoutCart(user, request);
    }
}

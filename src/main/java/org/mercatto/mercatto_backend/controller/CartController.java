package org.mercatto.mercatto_backend.controller;

import org.mercatto.mercatto_backend.dto.request.CartRequest;
import org.mercatto.mercatto_backend.dto.response.CartResponse;
import org.mercatto.mercatto_backend.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/create")
    public ResponseEntity<CartResponse> create(@RequestBody CartRequest request) {
        CartResponse response = cartService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<CartResponse> findById(@PathVariable long userId) {
        CartResponse response = cartService.findById(userId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{cartId}")
    public void delete(@PathVariable long cartId) {
        cartService.delete(cartId);
    }
}

package org.mercatto.mercatto_backend.service.impl;

import org.mercatto.mercatto_backend.dto.request.CartItemRequest;
import org.mercatto.mercatto_backend.dto.request.CartRequest;
import org.mercatto.mercatto_backend.dto.response.CartResponse;
import org.mercatto.mercatto_backend.mapper.CartMapper;
import org.mercatto.mercatto_backend.model.CartItemModel;
import org.mercatto.mercatto_backend.model.CartModel;
import org.mercatto.mercatto_backend.model.ProductModel;
import org.mercatto.mercatto_backend.model.UserModel;
import org.mercatto.mercatto_backend.repositories.CartRepository;
import org.mercatto.mercatto_backend.repositories.ProductRepository;
import org.mercatto.mercatto_backend.repositories.UserRepository;
import org.mercatto.mercatto_backend.service.CartService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository repository;

    private final CartMapper mapper;


    public CartServiceImpl(CartRepository repository, CartMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public CartResponse create(CartRequest request) {
        CartModel cart = mapper.toEntity(request);

        CartModel saved = repository.save(cart);

        return mapper.toResponse(saved);
    }

    @Override
    public CartResponse findById(Long id) {
        CartModel cart = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        return mapper.toResponse(cart);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);

    }
}

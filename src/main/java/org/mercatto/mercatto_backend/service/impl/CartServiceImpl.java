package org.mercatto.mercatto_backend.service.impl;

import org.mercatto.mercatto_backend.dto.request.CartItemRequest;
import org.mercatto.mercatto_backend.dto.request.CartRequest;
import org.mercatto.mercatto_backend.dto.request.OrderRequest;
import org.mercatto.mercatto_backend.dto.response.CartResponse;
import org.mercatto.mercatto_backend.dto.response.OrderResponse;
import org.mercatto.mercatto_backend.mapper.CartMapper;
import org.mercatto.mercatto_backend.model.CartItemModel;
import org.mercatto.mercatto_backend.model.CartModel;
import org.mercatto.mercatto_backend.model.ProductModel;
import org.mercatto.mercatto_backend.model.UserModel;
import org.mercatto.mercatto_backend.repositories.CartRepository;
import org.mercatto.mercatto_backend.repositories.ProductRepository;
import org.mercatto.mercatto_backend.repositories.UserRepository;
import org.mercatto.mercatto_backend.service.CartService;
import org.mercatto.mercatto_backend.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository repository;
    private final CartMapper mapper;
    private final OrderService orderService;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public CartServiceImpl(CartRepository repository, CartMapper mapper, OrderService orderService, UserRepository userRepository, ProductRepository productRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.orderService = orderService;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public CartResponse addItemToCart(CartRequest request) {
        UserModel user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        CartModel cart = repository.findByUser(user)
                .orElseGet(() -> {
                    CartModel newCart = new CartModel();
                    newCart.setUser(user);
                    return repository.save(newCart);
                });

        for (CartItemRequest itemRequest : request.getItems()) {
            ProductModel product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            Optional<CartItemModel> existingItem = cart.getItems().stream()
                    .filter(item -> item.getProduct().getId().equals(product.getId()))
                    .findFirst();

            if (existingItem.isPresent()) {
                // Atualiza a quantidade do item existente
                CartItemModel item = existingItem.get();
                item.setQuantity(item.getQuantity() + itemRequest.getQuantity());
            } else {
                // Cria um novo item no carrinho
                CartItemModel item = new CartItemModel();
                item.setCart(cart);
                item.setProduct(product);
                item.setQuantity(itemRequest.getQuantity());
                cart.getItems().add(item);
            }
        }

        CartModel savedCart = repository.save(cart);
        return mapper.toResponse(savedCart);
    }

    @Override
    public CartResponse findById(Long id) {
        CartModel cart = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carrinho nao encontrado"));
        return mapper.toResponse(cart);
    }

    @Override
    public void removeItemFromCart(Long userId, Long productId) {
        CartModel cart = repository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Carrinho nao encontrado"));

        cart.getItems().removeIf(item -> item.getProduct().getId().equals(productId));
        repository.save(cart);
    }

    @Override
    public OrderResponse checkoutCart(UserModel user, OrderRequest request) {
        CartModel cart = repository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Carrinho nao encontrado"));

        OrderResponse orderResponse = orderService.create(request);

        cart.getItems().clear();
        repository.save(cart);

        return orderResponse;
    }
}
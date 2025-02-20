package org.mercatto.mercatto_backend.service.impl;

import org.mercatto.mercatto_backend.dto.request.OrderRequest;
import org.mercatto.mercatto_backend.dto.response.OrderResponse;
import org.mercatto.mercatto_backend.mapper.OrderMapper;
import org.mercatto.mercatto_backend.model.OrderModel;
import org.mercatto.mercatto_backend.model.UserModel;
import org.mercatto.mercatto_backend.repositories.OrderRepository;
import org.mercatto.mercatto_backend.repositories.UserRepository;
import org.mercatto.mercatto_backend.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final UserRepository userRepository;
    private final OrderMapper mapper;

    public OrderServiceImpl(OrderRepository repository, UserRepository userRepository, OrderMapper mapper) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public OrderResponse create(OrderRequest request) {
        UserModel user = userRepository.findById(request.getUserId())
                .orElseThrow(()->new RuntimeException("User not found"));

        OrderModel order = new OrderModel();
        order.setUser(user);
        order.setPaymentMethod(request.getPaymentMethod());
        order.setDeliveryAddress(order.getDeliveryAddress());

        OrderModel saved = repository.save(order);
        return mapper.toResponse(saved);
    }

    @Override
    public List<OrderResponse> findAll() {
        return repository.findAll().stream().
                map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void updateOrderStatus(Long orderId, String status) {
        OrderModel order = repository.findById(orderId)
                .orElseThrow(()->new RuntimeException("Pedido nao encontrado"));
        order.setStatus(status);
        repository.save(order);
    }
}

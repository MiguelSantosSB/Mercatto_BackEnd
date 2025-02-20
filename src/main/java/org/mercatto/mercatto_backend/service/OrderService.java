package org.mercatto.mercatto_backend.service;

import org.mercatto.mercatto_backend.dto.request.OrderRequest;
import org.mercatto.mercatto_backend.dto.response.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse create(OrderRequest request);
    List<OrderResponse>  findAll();
    void updateOrderStatus(Long orderId, String status);
}

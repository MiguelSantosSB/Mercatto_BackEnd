package org.mercatto.mercatto_backend.mapper;

import org.mercatto.mercatto_backend.dto.response.OrderItemResponse;
import org.mercatto.mercatto_backend.dto.response.OrderResponse;
import org.mercatto.mercatto_backend.model.OrderItemModel;
import org.mercatto.mercatto_backend.model.OrderModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public OrderResponse toResponse(OrderModel order) {
        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setPaymentMethod(order.getPaymentMethod());
        response.setDeliveryAddress(order.getDeliveryAddress());
        response.setStatus(order.getStatus());
        response.setCreateAt(order.getCreatedAt());

        List<OrderItemResponse> items = order.getItems().stream()
                .map(this::toOrderItemResponse)
                .collect(Collectors.toList());
        response.setItems(items);

        return response;
    }

    private OrderItemResponse toOrderItemResponse(OrderItemModel item) {
        OrderItemResponse response = new OrderItemResponse();
        response.setProductId(item.getProduct().getId());
        response.setProductName(item.getProduct().getName());
        response.setProductPrice(item.getProduct().getPrice());
        response.setQuantity(item.getQuantity());
        return response;
    }
}

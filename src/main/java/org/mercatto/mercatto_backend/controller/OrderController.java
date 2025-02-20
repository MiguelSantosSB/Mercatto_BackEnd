package org.mercatto.mercatto_backend.controller;

import org.mercatto.mercatto_backend.dto.request.OrderRequest;
import org.mercatto.mercatto_backend.dto.response.OrderResponse;
import org.mercatto.mercatto_backend.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final  OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public OrderResponse create(@RequestBody OrderRequest orderRequest) {
        return orderService.create(orderRequest);
    }

    @GetMapping("/findAll")
    public List<OrderResponse> getAll() {
        return orderService.findAll();
    }

    @PutMapping("/{orderId}/status")
    public void updateStatus(@PathVariable Long orderId, @RequestParam String status) {
        orderService.updateOrderStatus(orderId, status);
    }
}

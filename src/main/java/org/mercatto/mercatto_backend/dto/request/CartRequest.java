package org.mercatto.mercatto_backend.dto.request;

import java.util.List;

public class CartRequest {
    private Long userId;
    private List<CartItemRequest> items;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<CartItemRequest> getItems() {
        return items;
    }

    public void setItems(List<CartItemRequest> items) {
        this.items = items;
    }
}

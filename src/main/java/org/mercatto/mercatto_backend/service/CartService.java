package org.mercatto.mercatto_backend.service;

import org.mercatto.mercatto_backend.dto.request.CartRequest;
import org.mercatto.mercatto_backend.dto.response.CartResponse;

public interface CartService {

    CartResponse create(CartRequest cartRequest);
    CartResponse findById(Long id);
    void delete(Long id);
}

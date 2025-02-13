package org.mercatto.mercatto_backend.service;

import org.mercatto.mercatto_backend.dto.request.ProductRequest;
import org.mercatto.mercatto_backend.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse create(ProductRequest request);
    ProductResponse findById(Long id);
    List<ProductResponse> findAll();
    ProductResponse update(Long id,ProductRequest request);
    void delete(Long id);
}

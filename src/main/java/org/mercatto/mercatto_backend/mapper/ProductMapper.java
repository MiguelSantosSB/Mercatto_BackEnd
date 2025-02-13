package org.mercatto.mercatto_backend.mapper;

import org.mercatto.mercatto_backend.dto.request.ProductRequest;
import org.mercatto.mercatto_backend.dto.response.ProductResponse;
import org.mercatto.mercatto_backend.model.ProductModel;
import org.mercatto.mercatto_backend.model.StoreModel;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductModel toEntity(ProductRequest request, StoreModel store) {
        ProductModel product = new ProductModel();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setStore(store);
        return product;
    }

    public ProductResponse toResponse(ProductModel product) {
        ProductResponse response = new ProductResponse();
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setStock(product.getStock());
        response.setStoreId(product.getStore().getId());
        return response;
    }
}

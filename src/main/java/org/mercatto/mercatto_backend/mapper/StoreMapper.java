package org.mercatto.mercatto_backend.mapper;

import org.mercatto.mercatto_backend.dto.request.StoreRequest;
import org.mercatto.mercatto_backend.dto.response.StoreResponse;
import org.mercatto.mercatto_backend.model.AddressModel;
import org.mercatto.mercatto_backend.model.ProductModel;
import org.mercatto.mercatto_backend.model.StoreModel;
import org.mercatto.mercatto_backend.model.UserModel;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class StoreMapper {

    public StoreModel toEntity(StoreRequest request, AddressModel address, UserModel user) {
       StoreModel store = new StoreModel();
       store.setName(request.getName());
       store.setDescription(request.getDescription());
       store.setCnpj(request.getCnpj());
       store.setTelephone(request.getTelephone());
       store.setAddress(address);
       store.setOwner(user);
       return store;
    }

    public StoreResponse toResponse(StoreModel store) {
        StoreResponse response = new StoreResponse();
        response.setId(store.getId());
        response.setName(store.getName());
        response.setDescription(store.getDescription());
        response.setCnpj(store.getCnpj());
        response.setTelephone(store.getTelephone());
        response.setAddress(store.getAddress().getId());
        response.setOwner(store.getOwner().getId());
        if (store.getProducts() != null) {
            response.setProducts(store.getProducts().stream()
                    .map(ProductModel::getId)
                    .collect(Collectors.toList()));
        }
        return response;
    }
}

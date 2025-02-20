package org.mercatto.mercatto_backend.repositories;

import org.mercatto.mercatto_backend.model.ProductModel;
import org.mercatto.mercatto_backend.model.StoreModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {
    Optional<ProductModel> findByName(String name);

    Optional<List<ProductModel>> findByStoreId(Long storeId);
}


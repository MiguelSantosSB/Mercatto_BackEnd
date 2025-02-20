package org.mercatto.mercatto_backend.repositories;

import org.mercatto.mercatto_backend.model.StoreModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<StoreModel, Long> {
    Optional<StoreModel> findByName(String name);

    Optional<StoreModel> findById(Long id);

    Optional<StoreModel> findByOwnerId(Long id);
}

package org.mercatto.mercatto_backend.repositories;

import org.mercatto.mercatto_backend.model.CartModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<CartModel, Long> {
    Optional<CartModel> findByUserId(Long userId);
}

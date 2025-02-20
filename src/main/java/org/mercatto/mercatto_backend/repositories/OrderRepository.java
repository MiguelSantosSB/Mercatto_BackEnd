package org.mercatto.mercatto_backend.repositories;

import org.mercatto.mercatto_backend.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderModel, Long> {
}

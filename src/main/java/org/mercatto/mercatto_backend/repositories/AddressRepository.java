package org.mercatto.mercatto_backend.repositories;

import org.mercatto.mercatto_backend.model.AddressModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<AddressModel, Long> {
    Optional<AddressModel> findByStreet(String street);
}

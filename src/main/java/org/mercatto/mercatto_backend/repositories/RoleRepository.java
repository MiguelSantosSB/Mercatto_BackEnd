package org.mercatto.mercatto_backend.repositories;

import org.mercatto.mercatto_backend.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel, Long> {
    Optional<RoleModel> findByName(String name);

    boolean existsByName(String name);


}

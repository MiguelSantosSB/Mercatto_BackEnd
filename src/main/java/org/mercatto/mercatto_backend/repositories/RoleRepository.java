package org.mercatto.mercatto_backend.repositories;

import org.mercatto.mercatto_backend.model.RoleModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<RoleModel, Long> {
}

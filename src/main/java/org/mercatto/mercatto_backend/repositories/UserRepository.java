package org.mercatto.mercatto_backend.repositories;

import org.mercatto.mercatto_backend.model.UserModel;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserModel, Long> {
}

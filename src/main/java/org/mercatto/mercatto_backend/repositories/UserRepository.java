package org.mercatto.mercatto_backend.repositories;

import org.mercatto.mercatto_backend.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByNumber(String number);
}

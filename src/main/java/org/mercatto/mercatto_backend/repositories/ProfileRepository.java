package org.mercatto.mercatto_backend.repositories;

import org.mercatto.mercatto_backend.model.ProfileModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends CrudRepository<ProfileModel, String> {



}

package org.mercatto.mercatto_backend.factory;

import org.mercatto.mercatto_backend.dto.request.UserRequest;
import org.mercatto.mercatto_backend.model.UserModel;

public interface UserFactory {
    UserModel createUser(UserRequest userRequest);
}

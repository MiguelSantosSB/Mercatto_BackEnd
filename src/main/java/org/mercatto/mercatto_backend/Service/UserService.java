package org.mercatto.mercatto_backend.Service;
import org.mercatto.mercatto_backend.dto.request.UserRequest;
import org.mercatto.mercatto_backend.dto.response.UserResponse;

import java.util.List;


public interface UserService {

    UserResponse create(UserRequest userRequest);
    UserResponse findById(Long id);
    List<UserResponse> findAll();
    UserResponse update(Long id, UserRequest userRequest);
    void delete(Long id);
}

package org.mercatto.mercatto_backend.mapper;

import org.mercatto.mercatto_backend.dto.request.UserRequest;
import org.mercatto.mercatto_backend.dto.response.UserResponse;
import org.mercatto.mercatto_backend.model.RoleModel;
import org.mercatto.mercatto_backend.model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserModel toEntity(UserRequest request, RoleModel role) {
       UserModel user = new UserModel();
       user.setName(request.getName());
       user.setEmail(request.getEmail());
       user.setNumber(request.getNumber());
       user.setPassword(request.getPassword());
       user.setCpf(request.getCpf());
       user.setRg(request.getRg());
       user.setRole(role);
       return user;
    }

    public UserResponse toResponse(UserModel user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setNumber(user.getNumber());
        response.setCpf(user.getCpf());
        response.setRg(user.getRg());
        response.setRole(user.getRole().getName());
        return response;
    }
}

package org.mercatto.mercatto_backend.mapper;

import org.mercatto.mercatto_backend.dto.request.RoleRequest;
import org.mercatto.mercatto_backend.dto.response.RoleResponse;
import org.mercatto.mercatto_backend.model.RoleModel;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    public RoleModel toRole(RoleRequest roleRequest) {
        RoleModel role = new RoleModel();
        role.setName(roleRequest.getName());
        role.setDescription(roleRequest.getDescription());
        return role;
    }

    public RoleResponse toRoleResponse(RoleModel roleModel) {
        RoleResponse response = new RoleResponse();
        response.setId(roleModel.getId());
        response.setName(roleModel.getName());
        response.setDescription(roleModel.getDescription());
        return response;
    }
}

package org.mercatto.mercatto_backend.service;

import org.mercatto.mercatto_backend.dto.request.RoleRequest;
import org.mercatto.mercatto_backend.dto.response.RoleResponse;

import java.util.List;

public interface RoleService {
    RoleResponse create(RoleRequest roleRequest);
    RoleResponse findById(Long id);
    List<RoleResponse> findAll();
    RoleResponse update(Long id ,RoleRequest roleRequest);
    void delete(Long id);
}

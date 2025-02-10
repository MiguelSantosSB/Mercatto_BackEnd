package org.mercatto.mercatto_backend.Service.impl;

import org.mercatto.mercatto_backend.Service.RoleService;
import org.mercatto.mercatto_backend.dto.request.RoleRequest;
import org.mercatto.mercatto_backend.dto.response.RoleResponse;
import org.mercatto.mercatto_backend.mapper.RoleMapper;
import org.mercatto.mercatto_backend.model.RoleModel;
import org.mercatto.mercatto_backend.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repository;
    private final RoleMapper mapper;

    public RoleServiceImpl(RoleRepository repository, RoleMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public RoleResponse create(RoleRequest request) {
        RoleModel role = mapper.toRole(request);
        RoleModel saved = repository.save(role);
        return mapper.toRoleResponse(saved);
    }

    @Override
    public RoleResponse findById(Long id) {
        RoleModel role = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        return mapper.toRoleResponse(role);
    }

    @Override
    public List<RoleResponse> findAll() {
        List<RoleModel> roles = repository.findAll();
        return roles.stream()
                .map(mapper::toRoleResponse)
                .collect(Collectors.toList());
    }

    @Override
    public RoleResponse update(Long id, RoleRequest request) {
        RoleModel role = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        role.setName(request.getName());
        role.setDescription(request.getDescription());

        RoleModel updated = repository.save(role);
        return mapper.toRoleResponse(updated);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}

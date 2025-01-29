package org.mercatto.mercatto_backend.Service.impl;

import org.mercatto.mercatto_backend.model.RoleModel;
import org.mercatto.mercatto_backend.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public RoleModel createNewRole(RoleModel role) {
        return roleRepository.save(role);
    }
}

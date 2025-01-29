package org.mercatto.mercatto_backend.controller;

import org.mercatto.mercatto_backend.Service.impl.RoleService;
import org.mercatto.mercatto_backend.model.RoleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping({"/createRole"})
    public RoleModel createNewRole(@RequestBody RoleModel role) {
        return roleService.createNewRole(role);
    }
}

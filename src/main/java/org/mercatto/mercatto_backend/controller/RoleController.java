package org.mercatto.mercatto_backend.controller;

import org.mercatto.mercatto_backend.Service.impl.RoleService;
import org.mercatto.mercatto_backend.dto.request.RoleRequest;
import org.mercatto.mercatto_backend.model.RoleModel;
import org.mercatto.mercatto_backend.repositories.RoleRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/role")
public class RoleController {

    private final RoleService service;

    private final RoleRepository repository;

    public RoleController(RoleService roleService, RoleRepository roleRepository) {
        this.service = roleService;
        this.repository = roleRepository;
    }

    @PostMapping({"/create"})
    public ResponseEntity create(@RequestBody RoleRequest body) {
        Optional<RoleModel> role = this.repository.findByName(body.getName());

        if (role.isPresent()) {
            return ResponseEntity.badRequest().body("Role já cadastrada");
        }

        RoleModel newRole = new RoleModel();
        newRole.setName(body.getName());
        newRole.setDescription(body.getDescription());

        try {
            RoleModel saved = service.save(newRole);
            return ResponseEntity.ok(saved);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar a nova role");
        }
    }
}

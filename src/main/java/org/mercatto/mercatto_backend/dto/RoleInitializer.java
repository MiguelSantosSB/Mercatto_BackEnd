package org.mercatto.mercatto_backend.dto;

import org.mercatto.mercatto_backend.model.RoleModel;
import org.mercatto.mercatto_backend.repositories.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RoleInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public RoleInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        createRoleIfNotExist("Admin", "Role de administrador criada automaticamente ao iniciar a aplicação.");
        createRoleIfNotExist("User", "Default profile for newly created user");
        createRoleIfNotExist("Vendor", "Default profile for newly created Vendor");
    }

    private void createRoleIfNotExist(String name, String description) {
        if (roleRepository.findByName(name).isEmpty()) {
            RoleModel role = new RoleModel();
            role.setName(name);
            role.setDescription(description);
            roleRepository.save(role);
        }
    }
}
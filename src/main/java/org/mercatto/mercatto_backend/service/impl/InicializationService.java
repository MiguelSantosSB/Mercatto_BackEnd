package org.mercatto.mercatto_backend.service.impl;

import org.mercatto.mercatto_backend.model.RoleModel;
import org.mercatto.mercatto_backend.model.UserModel;
import org.mercatto.mercatto_backend.repositories.RoleRepository;
import org.mercatto.mercatto_backend.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class InicializationService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public InicializationService(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void inicializeRoles() {
        List<RoleModel> rolesToCreate = Arrays.asList(
                new RoleModel("ADMIN", "Role para administrador"),
                new RoleModel("USER","Role padrão de usuario"),
                new RoleModel("OWNER","Role utilizada por lojas")
        );

        for (RoleModel role : rolesToCreate) {
            if (!roleRepository.existsByName(role.getName())) {
                roleRepository.save(role);
            }
        }
    }

    public void inicializeAdminUser() {
        String adminEmail = "admin@mercatto.com";
        if (!userRepository.findByEmail(adminEmail).isPresent()) {

            RoleModel adminRole = roleRepository.findByName("ADMIN")
                    .orElseThrow(() -> new RuntimeException("Role ADMIN não encontrada"));

            UserModel adminUser = new UserModel();
            adminUser.setName("Miguel Santos");
            adminUser.setEmail(adminEmail);
            adminUser.setPassword(passwordEncoder.encode("admin@123456"));
            adminUser.setRole(adminRole);

            userRepository.save(adminUser);
        }
    }
}

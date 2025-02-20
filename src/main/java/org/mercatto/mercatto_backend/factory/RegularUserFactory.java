package org.mercatto.mercatto_backend.factory;

import org.mercatto.mercatto_backend.dto.request.UserRequest;
import org.mercatto.mercatto_backend.model.RoleModel;
import org.mercatto.mercatto_backend.model.UserModel;
import org.mercatto.mercatto_backend.repositories.RoleRepository;
import org.springframework.stereotype.Component;

@Component
public class RegularUserFactory implements UserFactory {

    private final RoleRepository roleRepository;

    public RegularUserFactory(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public UserModel createUser(UserRequest userRequest) {
        RoleModel role = roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("User role not found"));
        
        UserModel user = new UserModel();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());
        user.setPassword(userRequest.getPassword());
        user.setRole(role);
        
        return user;
    }
}

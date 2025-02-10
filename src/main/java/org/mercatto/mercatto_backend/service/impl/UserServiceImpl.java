package org.mercatto.mercatto_backend.service.impl;

import org.mercatto.mercatto_backend.service.UserService;
import org.mercatto.mercatto_backend.dto.request.UserRequest;
import org.mercatto.mercatto_backend.dto.response.UserResponse;
import org.mercatto.mercatto_backend.mapper.UserMapper;
import org.mercatto.mercatto_backend.model.RoleModel;
import org.mercatto.mercatto_backend.model.UserModel;
import org.mercatto.mercatto_backend.repositories.RoleRepository;
import org.mercatto.mercatto_backend.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final RoleRepository roleRepository;
    private final UserMapper  mapper;
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepository repository, RoleRepository roleRepository, UserMapper mapper, PasswordEncoder encoder) {
        this.repository = repository;
        this.roleRepository = roleRepository;
        this.mapper = mapper;
        this.encoder = encoder;
    }

    @Override
    public UserResponse create(UserRequest request) {
        RoleModel role = roleRepository.findByName(request.getRole()).orElseThrow(() -> new RuntimeException("Role not found"));

        UserModel user = mapper.toEntity(request, role);
        user.setPassword(encoder.encode(request.getPassword()));
        UserModel saved = repository.save(user);

        return mapper.toResponse(saved);
    }

    @Override
    public UserResponse findById(Long id) {
        UserModel user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return mapper.toResponse(user);
    }

    @Override
    public List<UserResponse> findAll() {
        List<UserModel> users = repository.findAll();
        return users.stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse update(Long id, UserRequest request) {
        UserModel user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setNumber(request.getNumber());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setCpf(request.getCpf());
        user.setRg(request.getRg());

        if (request.getRole() != null) {
            RoleModel role = roleRepository.findByName(request.getRole())
                    .orElseThrow(() -> new RuntimeException("Role not found"));
            user.setRole(role);
        }

        UserModel saved = repository.save(user);
        return mapper.toResponse(saved);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
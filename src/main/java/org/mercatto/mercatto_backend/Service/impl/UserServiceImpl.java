package org.mercatto.mercatto_backend.Service.impl;

import org.mercatto.mercatto_backend.Service.UserService;
import org.mercatto.mercatto_backend.exception.DataAlreadyregisteredException;
import org.mercatto.mercatto_backend.model.RoleModel;
import org.mercatto.mercatto_backend.model.UserModel;
import org.mercatto.mercatto_backend.repositories.RoleRepository;
import org.mercatto.mercatto_backend.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserModel save(UserModel model) {

        if (userRepository.existsByEmail(model.getEmail())) {
            throw new DataAlreadyregisteredException("Já existe um usuário cadastrado com o EMAIL informado.");
        }
        if (userRepository.existsByNumber(model.getNumber())) {
            throw new DataAlreadyregisteredException("Já existe um usuário cadastrado com o NÚMERO informado.");
        }

        // criptografar a senha no banco de dados
        model.setPassword(passwordEncoder.encode(model.getPassword()));

        RoleModel roleUser = roleRepository.findById(2L).orElseThrow(() -> new RuntimeException("Role 'User' não encontrada."));

        Set<RoleModel> role = new HashSet<>();
        role.add(roleUser);
        model.setRole(role);

        return userRepository.save(model);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email));
    }
}
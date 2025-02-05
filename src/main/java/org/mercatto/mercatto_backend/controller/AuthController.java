package org.mercatto.mercatto_backend.controller;

import org.mercatto.mercatto_backend.Service.UserService;
import org.mercatto.mercatto_backend.Service.impl.TokenService;
import org.mercatto.mercatto_backend.dto.request.UserRequest;
import org.mercatto.mercatto_backend.exception.DataAlreadyregisteredException;
import org.mercatto.mercatto_backend.model.UserModel;
import org.mercatto.mercatto_backend.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository repository;
    private final TokenService tokenService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public AuthController(UserRepository repository, TokenService tokenService, UserService userService, AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.tokenService = tokenService;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequest userRequest) {
        try {
            // Autenticação com base no email e senha fornecidos
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userRequest.getEmail(), userRequest.getPassword()));

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            Optional<UserModel> optionalUser = repository.findByEmail(userDetails.getUsername());
            if (optionalUser.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não encontrado");
            }

            // Recupera user autenticado e gera o token
            UserModel user = optionalUser.get();
            String token = tokenService.generateToken(user);

            Map<String, String> response = new HashMap<>();
            response.put("email", user.getEmail());
            response.put("token", token);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Erro ao autenticar usuário" + e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserRequest body) {
        Optional<UserModel> user = this.repository.findByEmail(body.getEmail());

        if (user.isPresent()) {
            // return caso o email já exista
            return ResponseEntity.badRequest().body("E-mail já cadastrado");
        }

        UserModel newUser = new UserModel();
        newUser.setName(body.getName());
        newUser.setEmail(body.getEmail());
        newUser.setNumber(body.getNumber());
        newUser.setPassword(body.getPassword());

        try {
            UserModel savedUser = userService.save(newUser);

            return ResponseEntity.ok(savedUser);
        } catch (DataAlreadyregisteredException ex) {
            // Capturar exceções personalizadas
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            // Erros inesperados
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao registrar o usuário");
        }
    }
}
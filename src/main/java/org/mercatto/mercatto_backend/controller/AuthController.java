package org.mercatto.mercatto_backend.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.mercatto.mercatto_backend.service.AuthService;
import org.mercatto.mercatto_backend.dto.request.LoginRequest;
import org.mercatto.mercatto_backend.dto.response.JwtResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "login", description = "login do users e autenticação")
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest loginRequest) {
        String token = authService.login(loginRequest);
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
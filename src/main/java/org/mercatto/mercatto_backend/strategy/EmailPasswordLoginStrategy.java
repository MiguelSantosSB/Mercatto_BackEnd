package org.mercatto.mercatto_backend.strategy;

import org.mercatto.mercatto_backend.configuration.config.JwtUtil;
import org.mercatto.mercatto_backend.dto.request.LoginRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class EmailPasswordLoginStrategy implements LoginStrategy {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public EmailPasswordLoginStrategy(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public String login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtUtil.generateToken((User) authentication.getPrincipal());
    }
}

package org.mercatto.mercatto_backend.service.impl;

import org.mercatto.mercatto_backend.service.AuthService;
import org.mercatto.mercatto_backend.configuration.config.JwtUtil;
import org.mercatto.mercatto_backend.dto.request.LoginRequest;
import org.mercatto.mercatto_backend.strategy.LoginStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    private final Map<String, LoginStrategy> loginStrategies;

    @Autowired
    public AuthServiceImpl(Map<String, LoginStrategy> loginStrategies) {
        this.loginStrategies = loginStrategies;
    }

    @Override
    public String login(LoginRequest request) {
        String strategyKey = request.getRole() != null ? request.getRole().toLowerCase() + "LoginStrategy" : "emailPasswordLoginStrategy";
        LoginStrategy strategy = loginStrategies.get(strategyKey);
        if (strategy == null) {
            throw new IllegalArgumentException("Invalid login strategy");
        }
        return strategy.login(request);
    }
}

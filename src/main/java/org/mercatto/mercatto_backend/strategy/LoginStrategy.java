package org.mercatto.mercatto_backend.strategy;

import org.mercatto.mercatto_backend.dto.request.LoginRequest;

public interface LoginStrategy {
    String login(LoginRequest loginRequest);
}

package org.mercatto.mercatto_backend.service;

import org.mercatto.mercatto_backend.dto.request.LoginRequest;

public interface AuthService {
    String login (LoginRequest loginRequest);
}

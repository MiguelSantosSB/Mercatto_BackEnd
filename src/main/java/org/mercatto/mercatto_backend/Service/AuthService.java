package org.mercatto.mercatto_backend.Service;

import org.mercatto.mercatto_backend.dto.request.LoginRequest;
import org.mercatto.mercatto_backend.dto.response.UserResponse;

public interface AuthService {
    String login (LoginRequest loginRequest);
}

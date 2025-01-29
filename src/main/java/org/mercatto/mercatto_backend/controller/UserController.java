package org.mercatto.mercatto_backend.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.mercatto.mercatto_backend.Service.UserService;
import org.mercatto.mercatto_backend.dto.request.UserRequest;
import org.mercatto.mercatto_backend.dto.response.UserResponse;
import org.mercatto.mercatto_backend.mapper.UserMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "usuário", description = "Controlador para salvar e editar o dados de usuário")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public ResponseEntity<UserResponse> save(UserRequest request) {
        var response = UserMapper.insertRequestToModel(request);
        return ResponseEntity.ok().body(UserMapper.modelToInsertResponse(userService.save(response)));
    }
}

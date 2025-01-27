package org.mercatto.mercatto_backend.controller;

import jakarta.annotation.PostConstruct;
import org.mercatto.mercatto_backend.Service.impl.UserService;
import org.mercatto.mercatto_backend.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initProfileAndUsers() {
        userService.initProfilesAndUser();
    }

    @PostMapping({"/createUser"})
    public UserModel CreateUser(@RequestBody UserModel user) {
        return userService.createUser(user);
    }

    @GetMapping({"/forAdmin"})
    public String forAdmin() {
        return "This URL is only accessible to admin";
    }

    @GetMapping({"/forUser"})
    public String forUser() {
        return "This URL is only accessible to user";
    }


}

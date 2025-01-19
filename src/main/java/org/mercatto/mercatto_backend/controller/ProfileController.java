package org.mercatto.mercatto_backend.controller;

import org.mercatto.mercatto_backend.Service.impl.ProfileService;
import org.mercatto.mercatto_backend.model.ProfileModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {

    @Autowired
    private ProfileService profileService;


    @PostMapping({"/createProfile"})
    public ProfileModel createNewProfile(@RequestBody ProfileModel profile) {
        return profileService.createNewProfile(profile);
    }


}

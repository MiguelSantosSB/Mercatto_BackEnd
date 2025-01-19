package org.mercatto.mercatto_backend.Service.impl;

import org.mercatto.mercatto_backend.model.ProfileModel;
import org.mercatto.mercatto_backend.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public ProfileModel createNewProfile(ProfileModel profile) {
        return profileRepository.save(profile);
    }


}

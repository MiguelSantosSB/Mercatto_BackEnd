package org.mercatto.mercatto_backend.Service.impl;

import org.mercatto.mercatto_backend.model.ProfileModel;
import org.mercatto.mercatto_backend.model.UserModel;
import org.mercatto.mercatto_backend.repositories.ProfileRepository;
import org.mercatto.mercatto_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;


    public UserModel createUser(UserModel user) {
        return userRepository.save(user);
    }

    public void initProfilesAndUser(){
        ProfileModel adminProfile = new ProfileModel();
        adminProfile.setRoleName("Admin");
        adminProfile.setRoleDescription("Admin profile");
        profileRepository.save(adminProfile);

        ProfileModel vendorProfile = new ProfileModel();
        vendorProfile.setRoleName("Vendor");
        vendorProfile.setRoleDescription("Vendor profile");
        profileRepository.save(vendorProfile);

        ProfileModel userProfile = new ProfileModel();
        userProfile.setRoleName("User");
        userProfile.setRoleDescription("Default profile for newly created user");
        profileRepository.save(userProfile);

        UserModel adminUser = new UserModel();
        adminUser.setName("admin");
        adminUser.setEmail("admin@gmail.com");
        adminUser.setNumber("85 912345678");
        adminUser.setPassword("admin@pass");
        Set<ProfileModel> adminProfiles = new HashSet<>();
        adminProfiles.add(adminProfile);
        adminUser.setProfile(adminProfiles);
        userRepository.save(adminUser);

        UserModel userUser = new UserModel();
        userUser.setName("user");
        userUser.setEmail("user@gmail.com");
        userUser.setNumber("85 912345678");
        userUser.setPassword("user@pass");
        Set<ProfileModel> userProfiles = new HashSet<>();
        userProfiles.add(userProfile);
        userUser.setProfile(userProfiles);
        userRepository.save(userUser);

        UserModel vendorUser = new UserModel();
        vendorUser.setName("vendor");
        vendorUser.setEmail("vendor@gmail.com");
        vendorUser.setNumber("85 912345678");
        vendorUser.setPassword("vendor@pass");
        Set<ProfileModel> vendorProfiles = new HashSet<>();
        vendorProfiles.add(vendorProfile);
        vendorUser.setProfile(vendorProfiles);
        userRepository.save(vendorUser);


    }

}

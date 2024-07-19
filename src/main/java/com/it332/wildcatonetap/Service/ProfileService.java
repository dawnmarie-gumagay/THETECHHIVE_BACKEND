package com.it332.wildcatonetap.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it332.wildcatonetap.Entity.ProfileEntity;
import com.it332.wildcatonetap.Entity.UserEntity;
import com.it332.wildcatonetap.Repository.ProfileRepository;
import com.it332.wildcatonetap.Repository.UserRepository;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;

    public ProfileEntity saveProfilePicture(int userId, byte[] profilePicture) {
        UserEntity user = userRepository.findById(userId).orElse(null);
        ProfileEntity profile = profileRepository.findByUser(user);
        if (profile == null) {
            profile = new ProfileEntity();
            profile.setUser(user);
        }
        profile.setProfilePicture(profilePicture);
        return profileRepository.save(profile);
    }
    
    // other service methods
}

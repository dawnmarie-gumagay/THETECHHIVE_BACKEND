package WildCatOneTap.TECHHIVE.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import WildCatOneTap.TECHHIVE.Entity.ProfileEntity;
import WildCatOneTap.TECHHIVE.Repository.ProfileRepository;

@Service
public class ProfileService {

    private final ProfileRepository prepo; 

    @Autowired
    public ProfileService(ProfileRepository prepo) { 
        this.prepo = prepo;
    }

    // Create
    public ProfileEntity saveProfile(ProfileEntity profile) {
        return prepo.save(profile); 
    }

    // Read - Method to retrieve all profiles
    public List<ProfileEntity> getAllProfiles() {
        return prepo.findAll();
    }

    // Read - Method to retrieve a profile by its ID
    public Optional<ProfileEntity> getProfileById(int profileID) {
        return prepo.findById(profileID); 
    }

    // Update
    public ProfileEntity updateProfile(int profileID, ProfileEntity newProfileDetails) {
        ProfileEntity profile = prepo.findById(profileID).orElse(null);
        if (profile != null) {
            profile.setFirstname(newProfileDetails.getFirstname());
            profile.setLastname(newProfileDetails.getLastname());
            
            return prepo.save(profile);
        }
        return null;
    }

    // Delete
    public void deleteProfile(int profileID) {
        prepo.deleteById(profileID); 
    }
}

package WildCatOneTap.TECHHIVE.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import WildCatOneTap.TECHHIVE.Entity.ProfileEntity;
import WildCatOneTap.TECHHIVE.Service.ProfileService;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private final ProfileService pserv;

    @Autowired
    public ProfileController(ProfileService pserv) {
        this.pserv = pserv;
    }

    // Create
    @PostMapping("/save")
    public ProfileEntity saveProfile(@RequestBody ProfileEntity profile) {
        return pserv.saveProfile(profile);
    }

    // Read - Retrieve all profiles
    @GetMapping("/all")
    public List<ProfileEntity> getAllProfiles() {
        return pserv.getAllProfiles();
    }

    // Read - Retrieve a profile by its ID
    @GetMapping("/{profileID}")
    public Optional<ProfileEntity> getProfileById(@PathVariable int profileID) {
        return pserv.getProfileById(profileID);
    }

    // Update
    @PostMapping("/update/{profileID}")
    public ProfileEntity updateProfile(@PathVariable int profileID, @RequestBody ProfileEntity newProfileDetails) {
        return pserv.updateProfile(profileID, newProfileDetails);
    }

    // Delete
    @DeleteMapping("/delete/{profileID}")
    public void deleteProfile(@PathVariable int profileID) {
        pserv.deleteProfile(profileID);
    }
}

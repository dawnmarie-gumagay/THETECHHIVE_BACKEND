package com.it332.wildcatonetap.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.it332.wildcatonetap.Entity.ProfileEntity;
import com.it332.wildcatonetap.Entity.UserEntity;
import com.it332.wildcatonetap.Repository.ProfileRepository;
import com.it332.wildcatonetap.Repository.UserRepository;
import com.it332.wildcatonetap.Service.ProfileService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/user/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private UserRepository urepo;

    @Autowired
    private ProfileRepository prepo;

    @PostMapping("/uploadProfilePicture")
    public ProfileEntity uploadProfilePicture(@RequestParam("userId") int userId, @RequestParam("file") MultipartFile file) {
        byte[] profilePicture = null;
        try {
            profilePicture = file.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return profileService.saveProfilePicture(userId, profilePicture);
    }

    @GetMapping("/getProfilePicture/{userId}")
    public byte[] getProfilePicture(@PathVariable int userId) {
        UserEntity user = urepo.findById(userId).orElse(null);
        ProfileEntity profile = prepo.findByUser(user);
        return profile != null ? profile.getProfilePicture() : null;
    }

    // other controller methods
}

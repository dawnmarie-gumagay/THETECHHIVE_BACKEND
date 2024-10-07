package com.it332.wildcatonetap.Service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.it332.wildcatonetap.Entity.UserEntity;
import com.it332.wildcatonetap.Repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Create user
    public UserEntity insertUser(UserEntity user) {
        return userRepository.save(user);
    }

    // Get all users
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    // Get user by ID
    public UserEntity getUserById(long id) {
        return userRepository.findById((int) id)
                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + id));
    }

    // Update user
    public UserEntity updateUser(Integer userId, String newPassword, String currentPassword) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
        if (passwordEncoder.matches(currentPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            return userRepository.save(user);
        } else {
            throw new IllegalArgumentException("Incorrect current password");
        }
    }

    // Get user by username
    public UserEntity getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Get user by ID number
    public UserEntity getUserByIdNumber(String idNumber) {
        return userRepository.findByIdNumber(idNumber);
    }
}

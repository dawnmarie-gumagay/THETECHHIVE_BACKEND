package com.it332.wildcatonetap.Controller;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.it332.wildcatonetap.Entity.UserEntity;
import com.it332.wildcatonetap.Service.PasswordResetService;
import com.it332.wildcatonetap.Service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordResetService passwordResetService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Create user
    @PostMapping("/insertUser")
    public ResponseEntity<UserEntity> insertUser(@RequestBody UserEntity user) {
        // Hash password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok(userService.insertUser(user));
    }

    // Read all users
    @GetMapping("/getAllUsers")
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    // Get user by username
    @GetMapping("/getByUsername")
    public ResponseEntity<UserEntity> getUserByUsername(@RequestParam String username) {
        UserEntity user = userService.getUserByUsername(username);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    // Update user record
    @PutMapping("/updateUser")
    public ResponseEntity<?> updateUser(@RequestParam Integer userId,
                                        @RequestBody Map<String, String> requestBody) {
        String currentPassword = requestBody.get("currentPassword");
        String newPassword = requestBody.get("password");

        try {
            UserEntity updatedUser = userService.updateUser(userId, newPassword, currentPassword);
            return ResponseEntity.ok(updatedUser);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/requestPasswordReset")
    public ResponseEntity<String> requestPasswordReset(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        try {
            String resetCode = passwordResetService.generateResetCode();
            passwordResetService.sendResetCode(email, resetCode); // Now only this call
            return ResponseEntity.ok("Password reset email sent.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to send email: " + e.getMessage());
        }
    }
    
    // Verify reset code
    @PostMapping("/verifyResetCode")
    public ResponseEntity<String> verifyResetCode(@RequestParam String email, @RequestParam String resetCode) {
        boolean isValid = passwordResetService.verifyResetCode(email, resetCode);

        if (isValid) {
            return ResponseEntity.ok("Verification successful.");
        } else {
            return ResponseEntity.badRequest().body("Invalid verification code or user not found.");
        }
    }

    // Reset password
    @PostMapping("/resetPassword")
    public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String newPassword = request.get("newPassword");
        String confirmPassword = request.get("confirmPassword");

        if (!newPassword.equals(confirmPassword)) {
            return ResponseEntity.badRequest().body("Passwords do not match.");
        }

        try {
            passwordResetService.resetPassword(email, newPassword);
            return ResponseEntity.ok("Password reset successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Failed to reset password: " + e.getMessage());
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body("Failed to reset password: User not found.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to reset password: " + e.getMessage());
        }
    }

    // User login (can be enhanced)
    @PostMapping("/login")
    public ResponseEntity<UserEntity> login(@RequestBody Map<String, String> loginDetails) {
        String idNumber = loginDetails.get("idNumber");
        String password = loginDetails.get("password");

        UserEntity user = userService.getUserByIdNumber(idNumber);

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}

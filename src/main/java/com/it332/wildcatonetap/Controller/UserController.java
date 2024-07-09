package com.it332.wildcatonetap.Controller;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.it332.wildcatonetap.Entity.UserEntity;
import com.it332.wildcatonetap.Service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = ("http://localhost:3000"))
public class UserController {

    @Autowired
    UserService userv;

	@Autowired
    UserService userService;

	

    @GetMapping("/print")
    public String itWorks() {
        return "It works";
    }

    //Create
    @PostMapping("/insertUser")
    public UserEntity insertUser(@RequestBody UserEntity user) {
        return userv.insertUser(user);
    }

    //Read
    @GetMapping("/getAllUsers")
    public List<UserEntity> getAllUsers() {
        return userv.getAllUsers();
    }

    //U - Update a user record
    @PutMapping("/updateUser")
public ResponseEntity<?> updateUser(@RequestParam Integer userId, 
                                    @RequestBody Map<String, String> requestBody) {
    System.out.println("Received update request:");
    System.out.println("userId: " + userId);
    System.out.println("requestBody: " + requestBody);

    String currentPassword = requestBody.get("currentPassword");
    String newPassword = requestBody.get("password");

    try {
        UserEntity updatedUser = userv.updateUser(userId, newPassword, currentPassword);
        return ResponseEntity.ok(updatedUser);
    } catch (NoSuchElementException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    } catch (IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
   

    // Get user by ID number
    @GetMapping("/getByUsername")
    public ResponseEntity<UserEntity> getUserByUsername(@RequestParam String username) {
        UserEntity user = userService.getUserByUsername(username);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

}
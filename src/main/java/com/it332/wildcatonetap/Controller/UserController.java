package com.it332.wildcatonetap.Controller;

import java.util.List;
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
	public ResponseEntity<?> updateUser(@RequestParam int userId, @RequestBody UserEntity newUserDetails, @RequestParam String currentPassword) {
		try {
			UserEntity updatedUser = userv.updateUser(userId, newUserDetails, currentPassword);
			return ResponseEntity.ok(updatedUser);
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
}
	
	// Get user by username
	@GetMapping("/getByUsername")
	public ResponseEntity<?> getUserByUsername(@RequestParam String username) {
		try {
			UserEntity user = userv.getUserByUsername(username);
			if (user == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("User not found with username: " + username);
			}
			return ResponseEntity.ok(user);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("An error occurred while fetching the user: " + e.getMessage());
    }
}
	


}

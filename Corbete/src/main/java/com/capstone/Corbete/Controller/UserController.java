package com.capstone.Corbete.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.Corbete.Entity.UserEntity;
import com.capstone.Corbete.Service.UserService;

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
	public UserEntity updateUser(@RequestParam int userId, @RequestBody UserEntity newUserDetails) {
		return userv.updateUser(userId, newUserDetails);
	}
					
	// Get user by username
	@GetMapping("/getByUsername")
	public UserEntity getUserByUsername(@RequestParam String username) {
		return userv.getUserByUsername(username);
	}

}

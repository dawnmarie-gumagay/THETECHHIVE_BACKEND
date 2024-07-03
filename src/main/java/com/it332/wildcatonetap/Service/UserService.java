package com.it332.wildcatonetap.Service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it332.wildcatonetap.Entity.UserEntity;
import com.it332.wildcatonetap.Repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository urepo;
	
	//Create or insert user record in tblUsers
	public UserEntity insertUser(UserEntity user) {
		return urepo.save(user);
	}
	
	//Read all records in tblUsers
	public List<UserEntity> getAllUsers() {
		return urepo.findAll();
	}
	
	//U - update a user
	public UserEntity updateUser(int userId, UserEntity newUserDetails) {
		UserEntity user = urepo.findById(userId).orElseThrow(() -> new NoSuchElementException("User " + userId + " not found"));

		 // Update the user details
		 user.setUsername(newUserDetails.getUsername());
		 user.setEmail(newUserDetails.getEmail());
		 user.setPassword(newUserDetails.getPassword());
		 user.setFullName(newUserDetails.getFullName()); // Set full name
		 user.setIdNumber(newUserDetails.getIdNumber()); // Set ID number

		 return urepo.save(user);
	}

		// Get user by username
		public UserEntity getUserByUsername(String username) {
			return urepo.findByUsername(username);
		}

		// Get user by ID number
    public UserEntity getUserByIdNumber(String idNumber) {
			return urepo.findByIdNumber(idNumber);
	}
}

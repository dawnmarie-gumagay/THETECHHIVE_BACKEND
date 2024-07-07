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
	public UserEntity updateUser(int userId, String newPassword, String currentPassword) {
		try {
				UserEntity user = urepo.findById(userId).orElseThrow(() -> new NoSuchElementException("User " + userId + " not found"));

		// Validate the current password
		if (!user.getPassword().equals(currentPassword)) {	
			throw new IllegalArgumentException("Current password is incorrect.");
		}


		 // Update the user details
		 user.setPassword(newPassword);
		 
		 return urepo.save(user);
	} catch (NumberFormatException e) {
		throw new IllegalArgumentException("Invalid userId format. Please provide a valid integer userId.");
		}
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

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
	@SuppressWarnings("finally")
	public UserEntity updateUser(int userId, UserEntity newUserDetails) {
		UserEntity user = new UserEntity();
		try {
			//search the id number of the handset that will be updated
			user = urepo.findById(userId).get();
			
			//update the record
			user.setUsername(newUserDetails.getUsername());
			user.setEmail(newUserDetails.getEmail());
			user.setPassword(newUserDetails.getPassword());
		} catch(NoSuchElementException ex) {
			throw new NoSuchElementException("User " + userId + " does not exist!");
		
		} finally {
			return urepo.save(user);
		
		}
		
	}
	
	// Get user by username
	public UserEntity updateUser(int userId, UserEntity newUserDetails, String currentPassword) {
		UserEntity user = urepo.findById(userId)
			.orElseThrow(() -> new NoSuchElementException("User " + userId + " does not exist!"));
		
		if (!user.getPassword().equals(currentPassword)) {
			throw new IllegalArgumentException("Current password is incorrect");
		}
		
		user.setPassword(newUserDetails.getPassword());
		// Update other fields as necessary
		if (newUserDetails.getUsername() != null) user.setUsername(newUserDetails.getUsername());
		if (newUserDetails.getEmail() != null) user.setEmail(newUserDetails.getEmail());
		
		return urepo.save(user);
	}

	public boolean checkPassword(int userId, String currentPassword) {
		UserEntity user = urepo.findById(userId)
			.orElseThrow(() -> new NoSuchElementException("User " + userId + " does not exist!"));
		return user.getPassword().equals(currentPassword);
	}

	public UserEntity getUserByUsername(String username) {
		return urepo.findByUsername(username);
	}
}

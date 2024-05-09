package WildCatOneTap.TECHHIVE.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import WildCatOneTap.TECHHIVE.Entity.UserEntity;
import WildCatOneTap.TECHHIVE.Repository.UserRepository;

@Service
public class UserService {
	
	private final UserRepository urepo;
	
	public UserService(UserRepository urepo) {
		this.urepo = urepo;
	}
	
	// Create
	public UserEntity insertUser(UserEntity user) {
		return urepo.save(user);
	}
	
	// Read
	public List<UserEntity> getAll(){
		return urepo.findAll();
	}
	
	// Update
	public UserEntity updateUser(int userID, UserEntity newUserDetails) {
	    UserEntity user = null;
	    
	    try {
	        // Search for the user by ID
	        user = urepo.findById(userID).orElse(null);
	        
	        if (user != null) {
	            // Update the user details
	            user.setUsername(newUserDetails.getUsername());
	            user.setEmail(newUserDetails.getEmail());
	            // Set other fields you want to update
	            
	            // Save the updated user
	            user = urepo.save(user);
	        }
	    } catch (Exception e) {
	        // Handle any exceptions if necessary
	        e.printStackTrace();
	    }
	    
	    return user;
	}
	
	// Delete
	public String deleteUser(int userID) {
	    urepo.deleteById(userID);
		return null;
	}
}

package WildCatOneTap.TECHHIVE.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import WildCatOneTap.TECHHIVE.Entity.UserEntity;
import WildCatOneTap.TECHHIVE.Service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userv;
	
	//Create
	@PostMapping("/insteruser")
	public UserEntity insertUser(@RequestBody UserEntity user) {
		return userv.insertUser(user);
		
	}
	
	//Read
	@GetMapping("/updateUser")
	public UserEntity updateUser(@RequestParam int userID,@RequestBody UserEntity newUserDetails) {
		return userv.updateUser(userID, newUserDetails);
	}
	
	//Delete
	@DeleteMapping("/deleteUser/{userID}")
	public String deleteUser(@PathVariable int userID) {
			return userv.deleteUser(userID);
	}
}

package com.it332.wildcatonetap.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< Updated upstream
=======
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
>>>>>>> Stashed changes
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.it332.wildcatonetap.Entity.PostEntity;
import com.it332.wildcatonetap.Entity.UserEntity;
import com.it332.wildcatonetap.Service.PostService;
import com.it332.wildcatonetap.Service.UserService;

@RestController
@RequestMapping("/posts")
public class PostController {
	
	@Autowired
	private PostService postService;

	 @Autowired
    private UserService userService;
    
	
	@GetMapping
	public List<PostEntity> getAllPosts() {
		return postService.getAllPosts();
	}
	
	@GetMapping("/getByUsername")
    public ResponseEntity<UserEntity> getUserByUsername(@RequestParam String username) {
        UserEntity user = userService.getUserByUsername(username);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
		
    }
	@GetMapping("/{postId}")
	public Optional<PostEntity> getPostById(@PathVariable int postId) {
		return postService.getPostById(postId);
	}
	
	@PostMapping("/add")
    public ResponseEntity<PostEntity> addPost(@RequestBody PostEntity post) {
        PostEntity newPost = postService.createPost(post);
        return ResponseEntity.ok(newPost);
    }
	
	@PutMapping("/{postId}")
	public PostEntity updatePost(@PathVariable int postId, @RequestBody PostEntity postDetails) {
		return postService.updatePost(postId, postDetails);
	}
	
	@DeleteMapping("/{postId}")
	public void deletePost(@PathVariable int postId) {
		postService.deletePost(postId);
	}
	
	@PostMapping("/{postId}/like")
    public ResponseEntity<PostEntity> incrementLikes(@PathVariable int postId) {
        PostEntity updatedPost = postService.incrementLikes(postId);
        return ResponseEntity.ok(updatedPost);
    }
    
    @PostMapping("/{postId}/dislike")
    public ResponseEntity<PostEntity> incrementDislikes(@PathVariable int postId) {
        PostEntity updatedPost = postService.incrementDislikes(postId);
        return ResponseEntity.ok(updatedPost);
    }

	

	

}

package com.it332.wildcatonetap.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.it332.wildcatonetap.Entity.PostEntity;
import com.it332.wildcatonetap.Service.PostService;

@RestController
@RequestMapping("/posts")
@CrossOrigin(origins = ("http://localhost:3000"))
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@GetMapping
	public List<PostEntity> getAllPosts() {
		return postService.getAllPosts();
	}
	
	@GetMapping("/{postId}")
	public Optional<PostEntity> getPostById(@PathVariable int postId) {
		return postService.getPostById(postId);
	}
	
	@PostMapping("/add")
	public PostEntity addPost(@RequestBody PostEntity post) {
		return postService.createPost(post);
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
	public void incrementLikes(@PathVariable int postId) {
		postService.incrementLikes(postId);
	}
	
	@PostMapping("/{postId}/dislike")
	public void incrementDislikes(@PathVariable int postId) {
		postService.incrementDislikes(postId);
	}

}

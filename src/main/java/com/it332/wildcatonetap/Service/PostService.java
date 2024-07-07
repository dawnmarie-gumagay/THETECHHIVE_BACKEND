package com.it332.wildcatonetap.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it332.wildcatonetap.Entity.PostEntity;
import com.it332.wildcatonetap.Entity.UserEntity;
import com.it332.wildcatonetap.Repository.PostRepository;
import com.it332.wildcatonetap.Repository.UserRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
    private UserRepository userRepository;
    
    public List<PostEntity> getAllPosts() {
        return postRepository.findAll();
    }

	public UserEntity getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
	
	public Optional<PostEntity> getPostById(int postId) {
		return postRepository.findById(postId);
	}
	
	 public PostEntity createPost(PostEntity post) {
        UserEntity user = userRepository.findById(post.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        post.setFullName(user.getFullName());
        post.setIdNumber(user.getIdNumber());
        post.setProfilePicture(user.getProfilePicture());
        return postRepository.save(post);
    }
	
	public PostEntity updatePost(int postId, PostEntity postDetails) {
		PostEntity post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
		post.setContent(postDetails.getContent());
		post.setTimestamp(postDetails.getTimestamp());
		post.setUserId(postDetails.getUserId());
		post.setVerified(postDetails.isVerified());
		post.setLikes(postDetails.getLikes());
		post.setDislikes(postDetails.getDislikes());
		return postRepository.save(post);
	}
	
	public void deletePost(int postId) {
		postRepository.deleteById(postId);
	}
	
	public PostEntity incrementLikes(int postId) {
        PostEntity post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        post.setLikes(post.getLikes() + 1);
        return postRepository.save(post);
    }
    
    public PostEntity incrementDislikes(int postId) {
        PostEntity post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        post.setDislikes(post.getDislikes() + 1);
        return postRepository.save(post);
    }

}

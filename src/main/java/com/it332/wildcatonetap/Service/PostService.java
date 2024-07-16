package com.it332.wildcatonetap.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it332.wildcatonetap.Entity.CommentEntity;
import com.it332.wildcatonetap.Entity.PostEntity;
import com.it332.wildcatonetap.Entity.UserEntity;
import com.it332.wildcatonetap.Repository.CommentRepository;
import com.it332.wildcatonetap.Repository.PostRepository;
import com.it332.wildcatonetap.Repository.UserRepository;

@Service
public class PostService {
    
    @Autowired
    private PostRepository postRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CommentRepository commentRepository;
    
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
        System.out.println("Creating post: " + post);
        UserEntity user = userRepository.findById(post.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        post.setFullName(user.getFullName());
        post.setIdNumber(user.getIdNumber());
        post.setProfilePicture(user.getProfilePicture());
        post.setTimestamp(LocalDateTime.now());
        PostEntity savedPost = postRepository.save(post);
        System.out.println("Saved post: " + savedPost);
        return savedPost;
    }

    public PostEntity updatePost(int postId, PostEntity postDetails) {
        PostEntity post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        post.setContent(postDetails.getContent());
        post.setTimestamp(LocalDateTime.now());
        post.setUserId(postDetails.getUserId());
        post.setVerified(postDetails.isVerified());
        post.setLikes(postDetails.getLikes());
        post.setDislikes(postDetails.getDislikes());
        return postRepository.save(post);
    }
    
    public void deletePost(int postId) {
        postRepository.deleteById(postId);
    }
    
    public PostEntity toggleLike(int postId, int userId) {
        PostEntity post = postRepository.findById(postId)
            .orElseThrow(() -> new RuntimeException("Post not found"));
        
        if (post.getLikedBy().contains(userId)) {
            post.getLikedBy().remove(userId);
            post.setLikes(post.getLikes() - 1);
        } else {
            post.getLikedBy().add(userId);
            post.setLikes(post.getLikes() + 1);
            if (post.getDislikedBy().contains(userId)) {
                post.getDislikedBy().remove(userId);
                post.setDislikes(post.getDislikes() - 1);
            }
        }
        
        return postRepository.save(post);
    }
    
    public PostEntity toggleDislike(int postId, int userId) {
        PostEntity post = postRepository.findById(postId)
            .orElseThrow(() -> new RuntimeException("Post not found"));
        
        if (post.getDislikedBy().contains(userId)) {
            post.getDislikedBy().remove(userId);
            post.setDislikes(post.getDislikes() - 1);
        } else {
            post.getDislikedBy().add(userId);
            post.setDislikes(post.getDislikes() + 1);
            if (post.getLikedBy().contains(userId)) {
                post.getLikedBy().remove(userId);
                post.setLikes(post.getLikes() - 1);
            }
        }
        
        return postRepository.save(post);
    }

    // New methods for comment functionality
    public List<CommentEntity> getCommentsByPostId(int postId) {
        return commentRepository.findByPostId(postId);
    }

    public CommentEntity addComment(CommentEntity comment, int postId) {
        PostEntity post = postRepository.findById(postId)
            .orElseThrow(() -> new RuntimeException("Post not found"));
        comment.setPostId(postId);
        return commentRepository.save(comment);
    }
}
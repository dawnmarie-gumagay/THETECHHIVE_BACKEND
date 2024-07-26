package com.it332.wildcatonetap.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.it332.wildcatonetap.Entity.CommentEntity;
import com.it332.wildcatonetap.Entity.PostEntity;
import com.it332.wildcatonetap.Service.PostService;

@RestController
@RequestMapping("/posts")
@CrossOrigin(origins = "http://localhost:3000")
public class PostController {
    
    @Autowired
    private PostService postService;
    
    @GetMapping
    public List<PostEntity> getAllPosts() {
        return postService.getAllPosts();
    }
    
    @GetMapping("/{postId}")
    public ResponseEntity<PostEntity> getPostById(@PathVariable int postId) {
        Optional<PostEntity> post = postService.getPostById(postId);
        return post.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping("/add")
    public ResponseEntity<PostEntity> addPost(@RequestBody PostEntity post) {
        System.out.println("Received post: " + post);
        System.out.println("Received image: " + (post.getImage() != null ? "image present" : "no image"));
        PostEntity newPost = postService.createPost(post);
        System.out.println("Created post: " + newPost);
        return ResponseEntity.ok(newPost);
    }
    
    @PutMapping("/{postId}")
    public ResponseEntity<PostEntity> updatePost(@PathVariable int postId, @RequestBody PostEntity postDetails) {
        try {
            PostEntity updatedPost = postService.updatePost(postId, postDetails);
            return ResponseEntity.ok(updatedPost);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable int postId) {
        try {
            postService.softDeletePost(postId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/{postId}/like")
    public ResponseEntity<PostEntity> toggleLike(@PathVariable int postId, @RequestParam int userId) {
        try {
            PostEntity updatedPost = postService.toggleLike(postId, userId);
            return ResponseEntity.ok(updatedPost);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{postId}/dislike")
    public ResponseEntity<PostEntity> toggleDislike(@PathVariable int postId, @RequestParam int userId) {
        try {
            PostEntity updatedPost = postService.toggleDislike(postId, userId);
            return ResponseEntity.ok(updatedPost);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<CommentEntity>> getCommentsByPostId(@PathVariable int postId) {
        List<CommentEntity> comments = postService.getCommentsByPostId(postId);
        return ResponseEntity.ok(comments);
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentEntity> addComment(@PathVariable int postId, @RequestBody CommentEntity comment) {
        try {
            CommentEntity newComment = postService.addComment(comment, postId);
            return ResponseEntity.ok(newComment);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
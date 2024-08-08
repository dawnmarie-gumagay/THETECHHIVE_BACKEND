package com.example.admin_backend.Controller;

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

import com.example.admin_backend.Entity.CommentEntity;
import com.example.admin_backend.Entity.PostEntity;
import com.example.admin_backend.Service.PostService;

@RestController
@RequestMapping("/posts")
@CrossOrigin(origins = "http://localhost:3000")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<?> getAllPosts() {
        try {
            List<PostEntity> posts = postService.getAllPosts();
            System.out.println("Fetched posts: " + posts); // Add this line for logging
            return ResponseEntity.ok(posts);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error fetching posts: " + e.getMessage());
        }
    }

    @GetMapping("/{postId}")
    public ResponseEntity<?> getPostById(@PathVariable int postId) {
        try {
            Optional<PostEntity> post = postService.getPostById(postId);
            if (post.isPresent()) {
                return ResponseEntity.ok(post.get());
            } else {
                return ResponseEntity.status(404).body("Post not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error fetching post by ID: " + e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addPost(@RequestBody PostEntity post) {
        try {
            System.out.println("Received post: " + post);
            System.out.println("Received image: " + (post.getImage() != null ? "image present" : "no image"));
            PostEntity newPost = postService.createPost(post);
            System.out.println("Created post: " + newPost);
            return ResponseEntity.ok(newPost);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error adding post: " + e.getMessage());
        }
    }

    @PutMapping("/{postId}")
    public ResponseEntity<?> updatePost(@PathVariable int postId, @RequestBody PostEntity postDetails) {
        try {
            PostEntity updatedPost = postService.updatePost(postId, postDetails);
            return ResponseEntity.ok(updatedPost);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Post not found");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error updating post: " + e.getMessage());
        }
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable int postId) {
        try {
            postService.softDeletePost(postId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Post not found");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error deleting post: " + e.getMessage());
        }
    }

    @PostMapping("/{postId}/like")
    public ResponseEntity<?> toggleLike(@PathVariable int postId, @RequestParam int adminId) {
        try {
            PostEntity updatedPost = postService.toggleLike(postId, adminId);
            return ResponseEntity.ok(updatedPost);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Post not found");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error toggling like: " + e.getMessage());
        }
    }

    @PostMapping("/{postId}/dislike")
    public ResponseEntity<?> toggleDislike(@PathVariable int postId, @RequestParam int adminId) {
        try {
            PostEntity updatedPost = postService.toggleDislike(postId, adminId);
            return ResponseEntity.ok(updatedPost);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Post not found");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error toggling dislike: " + e.getMessage());
        }
    }

    @GetMapping("/{postId}/comments")
    public ResponseEntity<?> getCommentsByPostId(@PathVariable int postId) {
        try {
            List<CommentEntity> comments = postService.getCommentsByPostId(postId);
            return ResponseEntity.ok(comments);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error fetching comments: " + e.getMessage());
        }
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<?> addComment(@PathVariable int postId, @RequestBody CommentEntity comment) {
        try {
            CommentEntity newComment = postService.addComment(comment, postId);
            return ResponseEntity.ok(newComment);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body("Post not found");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error adding comment: " + e.getMessage());
        }
    }
}

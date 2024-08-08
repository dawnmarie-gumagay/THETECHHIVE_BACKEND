package com.example.admin_backend.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.admin_backend.Entity.CommentEntity;
import com.example.admin_backend.Entity.PostEntity;
import com.example.admin_backend.Entity.ProfileEntity;
import com.example.admin_backend.Entity.AdminEntity;
import com.example.admin_backend.Repository.CommentRepository;
import com.example.admin_backend.Repository.PostRepository;
import com.example.admin_backend.Repository.ProfileRepository;
import com.example.admin_backend.Repository.AdminRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ProfileRepository profileRepository;

    public List<PostEntity> getAllPosts() {
        try {
            List<PostEntity> posts = postRepository.findByIsDeletedFalse();
            System.out.println("Fetched posts from DB: " + posts);
            return posts;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching posts", e);
        }
    }

    public AdminEntity getAdminByAdminname(String adminname) {
        try {
            return adminRepository.findByAdminname(adminname);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching admin by adminname", e);
        }
    }

    public Optional<PostEntity> getPostById(int postId) {
        try {
            return postRepository.findByPostIdAndIsDeletedFalse(postId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching post by ID", e);
        }
    }

    public PostEntity createPost(PostEntity post) {
        try {
            System.out.println("Creating post: " + post);
            AdminEntity admin = adminRepository.findById(post.getAdminId())
                .orElseThrow(() -> new RuntimeException("Admin not found"));

            // Fetch the latest profile details
            ProfileEntity profile = profileRepository.findByAdmin(admin);
            if (profile != null) {
                post.setFullName(admin.getFullName());
                post.setProfile(profile); // Set the ProfileEntity object
            }

            post.setTimestamp(LocalDateTime.now());
            post.setDeleted(false);
            PostEntity savedPost = postRepository.save(post);
            System.out.println("Saved post: " + savedPost);
            return savedPost;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error creating post", e);
        }
    }

    public PostEntity updatePost(int postId, PostEntity postDetails) {
        try {
            PostEntity post = postRepository.findByPostIdAndIsDeletedFalse(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

            // Fetch the latest profile details for the admin
            AdminEntity admin = adminRepository.findById(postDetails.getAdminId())
                .orElseThrow(() -> new RuntimeException("Admin not found"));
            ProfileEntity profile = profileRepository.findByAdmin(admin);
            if (profile != null) {
                post.setFullName(admin.getFullName());
                post.setProfile(profile); // Set the ProfileEntity object
            }

            post.setContent(postDetails.getContent());
            post.setTimestamp(LocalDateTime.now());
            post.setAdminId(postDetails.getAdminId());
            post.setVerified(postDetails.isVerified());
            post.setLikes(postDetails.getLikes());
            post.setDislikes(postDetails.getDislikes());
            return postRepository.save(post);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error updating post", e);
        }
    }

    @Transactional
    public void softDeletePost(int postId) {
        try {
            PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
            post.setDeleted(true);
            postRepository.save(post);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error deleting post", e);
        }
    }

    public PostEntity toggleLike(int postId, int adminId) {
        try {
            PostEntity post = postRepository.findByPostIdAndIsDeletedFalse(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

            if (post.getLikedBy().contains(adminId)) {
                post.getLikedBy().remove(adminId);
                post.setLikes(post.getLikes() - 1);
            } else {
                post.getLikedBy().add(adminId);
                post.setLikes(post.getLikes() + 1);
                if (post.getDislikedBy().contains(adminId)) {
                    post.getDislikedBy().remove(adminId);
                    post.setDislikes(post.getDislikes() - 1);
                }
            }

            return postRepository.save(post);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error toggling like", e);
        }
    }

    public PostEntity toggleDislike(int postId, int adminId) {
        try {
            PostEntity post = postRepository.findByPostIdAndIsDeletedFalse(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

            if (post.getDislikedBy().contains(adminId)) {
                post.getDislikedBy().remove(adminId);
                post.setDislikes(post.getDislikes() - 1);
            } else {
                post.getDislikedBy().add(adminId);
                post.setDislikes(post.getDislikes() + 1);
                if (post.getLikedBy().contains(adminId)) {
                    post.getLikedBy().remove(adminId);
                    post.setLikes(post.getLikes() - 1);
                }
            }

            return postRepository.save(post);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error toggling dislike", e);
        }
    }

    public List<CommentEntity> getCommentsByPostId(int postId) {
        try {
            return commentRepository.findByPostIdAndIsDeletedFalse(postId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error fetching comments", e);
        }
    }

    public CommentEntity addComment(CommentEntity comment, int postId) {
        try {
            @SuppressWarnings("unused")
            PostEntity post = postRepository.findByPostIdAndIsDeletedFalse(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));
            comment.setPostId(postId);
            return commentRepository.save(comment);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error adding comment", e);
        }
    }
}

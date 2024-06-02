package com.capstone.Corbete.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.Corbete.Entity.PostEntity;
import com.capstone.Corbete.Repository.PostRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<PostEntity> getAllPosts() {
        return postRepository.findAll();
    }

    public Optional<PostEntity> getPostById(int postId) {
        return postRepository.findById(postId);
    }

    public PostEntity createPost(PostEntity post) {
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

    public void incrementLikes(int postId) {
        PostEntity post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        post.setLikes(post.getLikes() + 1);
        postRepository.save(post);
    }

    public void incrementDislikes(int postId) {
        PostEntity post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        post.setDislikes(post.getDislikes() + 1);
        postRepository.save(post);
    }
}

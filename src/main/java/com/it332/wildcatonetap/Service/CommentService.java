package com.it332.wildcatonetap.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it332.wildcatonetap.Entity.CommentEntity;
import com.it332.wildcatonetap.Entity.PostEntity;
import com.it332.wildcatonetap.Repository.CommentRepository;
import com.it332.wildcatonetap.Repository.PostRepository;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    public List<CommentEntity> getCommentsByPostId(int postId) {
        return commentRepository.findByPostId(postId);
    }

    public CommentEntity addComment(CommentEntity comment) {
        comment.setTimestamp(LocalDateTime.now());
        System.out.println("Saving comment: " + comment);
        CommentEntity savedComment = commentRepository.save(comment);
        System.out.println("Saved comment: " + savedComment);
        return savedComment;
    }

    public boolean deleteComment(int commentId, int userId) {
        Optional<CommentEntity> commentOpt = commentRepository.findById(commentId);
        
        if (commentOpt.isPresent()) {
            CommentEntity comment = commentOpt.get();
            Optional<PostEntity> postOpt = postRepository.findById(comment.getPostId());
            
            if (postOpt.isPresent()) {
                PostEntity post = postOpt.get();
                
                // Check if the user is the comment owner or the post owner
                if (comment.getUserId() == userId || post.getUserId() == userId) {
                    commentRepository.deleteById(commentId);
                    return true;
                }
            }
        }
        
        return false;
    }
}
package com.it332.wildcatonetap.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it332.wildcatonetap.Entity.CommentEntity;
import com.it332.wildcatonetap.Repository.CommentRepository;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<CommentEntity> getCommentsByPostId(int postId) {
        return commentRepository.findByPostId(postId);
    }

    public CommentEntity addComment(CommentEntity comment) {
        return commentRepository.save(comment);
    }
}

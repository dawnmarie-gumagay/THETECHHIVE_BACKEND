package com.it332.wildcatonetap.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.it332.wildcatonetap.Entity.CommentEntity;
import com.it332.wildcatonetap.Service.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/{postId}")
    public List<CommentEntity> getComments(@PathVariable int postId) {
        return commentService.getCommentsByPostId(postId);
    }

    @PostMapping("/add")
    public CommentEntity addComment(@RequestBody CommentEntity comment) {
        return commentService.addComment(comment);
    }
}

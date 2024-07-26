package com.it332.wildcatonetap.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.it332.wildcatonetap.Entity.CommentEntity;
import com.it332.wildcatonetap.Service.CommentService;

@RestController
@RequestMapping("/comments")
@CrossOrigin(origins = "http://localhost:3000")
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

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable int commentId, @RequestParam int userId) {
        boolean deleted = commentService.softDeleteComment(commentId, userId);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body("Unable to delete comment. You may not have permission.");
        }
    }
}

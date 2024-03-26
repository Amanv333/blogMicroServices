package com.comment.comment.controller;

import com.comment.comment.entity.Comment;
import com.comment.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    private CommentService commentService ;
    @PostMapping
    public ResponseEntity<?> saveComment(@RequestBody Comment comment){
        Comment saved = commentService.saveComment(comment);
        return new ResponseEntity<>(saved, HttpStatus.OK);
    }
    @GetMapping("/{postId}")
    public List<Comment> getAllComment(@PathVariable String postId){
        List<Comment> comments = commentService.getAllComment(postId);
        return comments;
    }

}

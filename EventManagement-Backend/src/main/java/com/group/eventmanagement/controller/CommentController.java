package com.group.eventmanagement.controller;

import com.group.eventmanagement.model.User;
import com.group.eventmanagement.model.Comment;
import com.group.eventmanagement.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping(value= {
        "/posts/{postId}/comments",
        "/posts/{postId}/comments/"
    })
    public Comment createComment(
        @PathVariable("postId") Long postId,
        @RequestParam(name="content")String content, 
        @RequestParam(name="commenterUsername")String commenterUsername
        ) throws IllegalArgumentException{
            Comment newComment = commentService.createComment(content, postId, commenterUsername);
            return newComment;
    }

    @GetMapping(value= {
        "/comments/{commentId}",
        "/comments/{commentId}/"
    })
    public Comment getComment(@PathVariable("commentId") Long commentId) throws IllegalArgumentException {
        return commentService.getComment(commentId);
    }
}

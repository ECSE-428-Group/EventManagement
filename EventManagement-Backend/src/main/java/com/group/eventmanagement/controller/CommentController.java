package com.group.eventmanagement.controller;

import com.group.eventmanagement.model.User;
import com.group.eventmanagement.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
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
        @RequestParam(name="content")String content, 
        @RequestParam(name="commenter")User commenter
        ) throws IllegalArgumentException{
            // TODO: Get post
            Post post = null;
            // TODO: Ask how authorization works
            Comment newComment = commentService.createComment(content, post, commenter);
            return newComment;
    }
}

package com.group.eventmanagement.service;

import javax.transaction.Transactional;

import com.group.eventmanagement.repository.CommentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.eventmanagement.model.Comment;
import com.group.eventmanagement.model.Post;
import com.group.eventmanagement.model.User;

@Service
public class CommentService {

    CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Transactional
    public Comment createComment(String content, Post post, User commenter) {
        String error = "";

        // Input validation
        if(content == null || content.trim().length() <= 0) {
            error += "Comment cannot be empty! ";
        }
        if(post == null) {
            error += "Comment must be linked to a post! ";
        }
        if(commenter == null) {
            error += "Comment must be linked to a user! ";
        }

        error = error.trim();
		if(error.length() > 0) {
			throw new IllegalArgumentException(error);
		}

        Comment newComment = new Comment();
        newComment.setContent(content);
        newComment.setPost(post);
        newComment.setCommenter(commenter);

        commentRepository.save(newComment);
        
        return newComment;
    }
}

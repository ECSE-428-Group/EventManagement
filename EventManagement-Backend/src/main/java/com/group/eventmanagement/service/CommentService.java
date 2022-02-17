package com.group.eventmanagement.service;

import javax.transaction.Transactional;

import com.group.eventmanagement.repository.CommentRepository;
import com.group.eventmanagement.repository.PostRepository;
import com.group.eventmanagement.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.eventmanagement.model.Comment;
import com.group.eventmanagement.model.Post;
import com.group.eventmanagement.model.User;

@Service
public class CommentService {

    CommentRepository commentRepository;
    PostRepository postRepository;
    UserRepository userRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Comment createComment(String content, Long postId, String commenterUsername) {
        String error = "";

        // Input validation
        if(content == null || content.trim().length() <= 0) {
            error += "Comment cannot be empty! ";
        }
        if(postId == null || !postRepository.existsById(postId)) {
            error += "Comment must be linked to a post! ";
        }
        if(commenterUsername == null || !userRepository.existsByUsername(commenterUsername)) {
            error += "Comment must be linked to a user! ";
        }

        error = error.trim();
		if(error.length() > 0) {
			throw new IllegalArgumentException(error);
		}

        // Get post and commenter
        Post post = postRepository.findByPostId(postId);
        User commenter = userRepository.findUserByUsername(commenterUsername);
        
        Comment newComment = new Comment();
        newComment.setContent(content);
        newComment.setPost(post);
        newComment.setCommenter(commenter);

        commentRepository.save(newComment);
        
        return newComment;
    }

    @Transactional
    public Comment getComment(Long id) {
        return commentRepository.findCommentById(id);
    }
}

package com.group.eventmanagement.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.group.eventmanagement.model.Comment;

public interface CommentRepository extends CrudRepository<Comment, String>{

	Comment findCommentById(Long id);
	Boolean existsById(Long id);
	List<Comment> findAll();
}

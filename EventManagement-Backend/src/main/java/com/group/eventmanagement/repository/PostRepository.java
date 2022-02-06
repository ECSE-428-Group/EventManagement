package com.group.eventmanagement.repository;

import java.util.List;

import com.group.eventmanagement.model.Comment;
import com.group.eventmanagement.model.Event;
import com.group.eventmanagement.model.Post;
import com.group.eventmanagement.model.User;

import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository <Post, Long> {

    Post findByPostId (Long postId);
    List<Post> getByUser (User user);
    List<Post> getByEvent (Event event);
    List<Comment> getCommentsByPostId (Long postId);

}

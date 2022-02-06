package com.group.eventmanagement.repository;

import java.util.List;

import com.group.eventmanagement.model.Post;

import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository <Post, Long> {

    Post findByPostId (Long postId);
    Boolean existsByPostId (Long postId);
    List<Post> findAll ();

}

package com.group.eventmanagement.service;

import com.group.eventmanagement.model.Event;
import com.group.eventmanagement.model.Post;
import com.group.eventmanagement.model.User;
import com.group.eventmanagement.repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    private boolean isNullOrEmptyString(String str) {
		return (str == null) || (str.trim().length() ==0);
	}
    public Post createPost(Event event, String title, String description, User user ){
        String error ="";
        if(event == null){
            error+="Post needs to be associated with an event! ";
        }
        if(user == null){
            error+="Post needs to be associated with a user! ";
        }
        if(isNullOrEmptyString(title)){
            error+="Post needs to have a title! ";
        }
        if(isNullOrEmptyString(description)){
            error+="Post needs to have a description! ";
        }
        if(error.length()>0){
            throw new IllegalArgumentException(error);
        }

        Post newPost = new Post();
        newPost.setTitle(title);
        newPost.setDescription(description);
        newPost.setUser(user);
        newPost.setEvent(event);
        postRepository.save(newPost);
        return newPost;


    }

    public Post getPostById(Long postId){
        if(postId==null){
            throw new IllegalArgumentException("An Id must be passed");
        }else if (!postRepository.existsByPostId(postId)){
            throw new IllegalArgumentException("No posts exist with the specified Id");
        }
        Post post = postRepository.findByPostId(postId);
        return post;
    }
}

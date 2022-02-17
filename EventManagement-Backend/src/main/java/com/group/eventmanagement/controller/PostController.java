package com.group.eventmanagement.controller;

import com.group.eventmanagement.model.Event;
import com.group.eventmanagement.model.Post;
import com.group.eventmanagement.model.User;
import com.group.eventmanagement.service.EventService;
import com.group.eventmanagement.service.PostService;
import com.group.eventmanagement.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class PostController {
    private PostService postService;
    private EventService eventService;
    private UserService userService;

    @Autowired
    public PostController(PostService postService, UserService userService, EventService eventService){
        this.postService=postService;
        this.eventService = eventService;
        this.userService = userService;
    }

    @PostMapping(value = {
        "/post",
        "/post/"
        })
    public Post createPost(
        @RequestParam(name = "title") String title,
        @RequestParam(name = "description") String description,
        @RequestParam(name = "eventId") Long eventId,
        @RequestParam(name = "username") String username
    ) throws IllegalArgumentException{
        Event event = eventService.getEventById(eventId);
        User user = userService.getUserByUsername(username);
        Post newPost = postService.createPost(event, title, description, user);
        return newPost;
    }
}

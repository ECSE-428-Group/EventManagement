package com.group.eventmanagement.repository;

import java.util.List;

import com.group.eventmanagement.model.Comment;
import com.group.eventmanagement.model.Event;
import com.group.eventmanagement.model.Post;
import com.group.eventmanagement.model.Tag;
import com.group.eventmanagement.model.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository <User, String> {

    User findUserByUsername (String username);
    Boolean existsByUsername (String username);
    List<Tag> getTagsByUsername (String username);
    List<Event> getOrganizedEventsByUsername (String username);
    List<Event> getAttendingEventsByUsername (String username);
    List<Post> getPostsByUsername (String username);
    List<Comment> getCommentsByUsername (String username);


}

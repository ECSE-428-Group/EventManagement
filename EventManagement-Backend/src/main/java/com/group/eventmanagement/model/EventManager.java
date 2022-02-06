package com.group.eventmanagement.model;

import javax.persistence.*;
import java.util.*;

@Entity
public class EventManager
{

    private Long eventManagerId;
    @Id
    @GeneratedValue
    public Long getEventManagerId() { return eventManagerId; }
    public void setEventManagerId(Long eventManagerId) { this.eventManagerId = eventManagerId; }

    ///////////////////////////////////////////////////////////////////////////

    private List<User> users;
    @OneToMany(cascade = CascadeType.ALL)
    public List<User> getUsers() {
        return this.users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }

    ///////////////////////////////////////////////////////////////////////////

    private List<Admin> admins;
    @OneToMany(cascade = CascadeType.ALL)
    public List<Admin> getAdmins() {
        return this.admins;
    }
    public void setAdmins(List<Admin> admins) {
        this.admins = admins;
    }

    ///////////////////////////////////////////////////////////////////////////

    private List<Event> events;
    @OneToMany(cascade = CascadeType.ALL)
    public List<Event> getEvents() {
        return this.events;
    }
    public void setEvents(List<Event> events) {
        this.events = events;
    }

    ///////////////////////////////////////////////////////////////////////////

    private List<Post> posts;
    @OneToMany(cascade = CascadeType.ALL)
    public List<Post> getPosts() {
        return this.posts;
    }
    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    ///////////////////////////////////////////////////////////////////////////

    private List<Tag> tags;
    @OneToMany(cascade = CascadeType.ALL)
    public List<Tag> getTags() {
        return this.tags;
    }
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    ///////////////////////////////////////////////////////////////////////////

    private List<Comment> comments;
    @OneToMany(cascade = CascadeType.ALL)
    public List<Comment> getComments() {
        return this.comments;
    }
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

}
package com.group.eventmanagement.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "posts")
public class Post
{

    private Long postId;
    @Id
    @GeneratedValue
    public Long getPostId() {
        return this.postId;
    }
    public void setPostId(Long postId) {
        this.postId = postId;
    }

    ///////////////////////////////////////////////////////////////////////////

    private String title;
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    ///////////////////////////////////////////////////////////////////////////

    private String description;
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    ///////////////////////////////////////////////////////////////////////////

    private User user;
    @ManyToOne(optional = false)
    public User getUser() { return this.user; }
    public void setUser(User user) { this.user = user; }

    ///////////////////////////////////////////////////////////////////////////

    private Event event;
    @ManyToOne(optional = false)
    public Event getEvent() {
        return this.event;
    }
    public void setEvent(Event event) {
        this.event = event;
    }

    ///////////////////////////////////////////////////////////////////////////

    private List<Comment> comments;
    @OneToMany(targetEntity = Comment.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "post")
    public List<Comment> getComments() {
        return this.comments;
    }
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

}

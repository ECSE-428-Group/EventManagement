package com.group.eventmanagement.model;

import javax.persistence.*;
import java.util.*;
import java.sql.Timestamp;

@Entity
@Table(name = "users")
public class User extends Person
{

    @Id
    public String getUsername() {
        return super.getUsername();
    }
    public void setUsername(String username) {
        super.setUsername(username);
    }

    ///////////////////////////////////////////////////////////////////////////

    private boolean isVaccinated;
    public boolean getVaccinationStatus() {
        return this.isVaccinated;
    }
    public void setVaccinationStatus(boolean isVaccinated) {
        this.isVaccinated = isVaccinated;
    }

    ///////////////////////////////////////////////////////////////////////////

    private Timestamp birthday;
    public Timestamp getBirthday() {
        return this.birthday;
    }
    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }

    ///////////////////////////////////////////////////////////////////////////

    private List<Tag> tags;
    @ManyToMany
    public List<Tag> getTags() {
        return this.tags;
    }
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    ///////////////////////////////////////////////////////////////////////////

    private List<Post> posts;
    @OneToMany(targetEntity = Post.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    public List<Post> getPosts() {
        return this.posts;
    }
    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    ///////////////////////////////////////////////////////////////////////////

    private List<Event> organizedEvents;
    @ManyToMany(mappedBy = "organizers")
    public List<Event> getEvents() {return this.organizedEvents; }
    public void setEvents(List<Event> organizedEvents) { this.organizedEvents = organizedEvents; }

    ///////////////////////////////////////////////////////////////////////////

    private List<Event> attendingEvents;
    @ManyToMany(mappedBy = "attendees")
    public List<Event> getAttendingEvents() {
        return this.attendingEvents;
    }
    public void setAttendingEvents(List<Event> organizedEvents) {
        this.attendingEvents = attendingEvents;
    }

    ///////////////////////////////////////////////////////////////////////////

    private List<Comment> comments;
    @OneToMany(targetEntity = Comment.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "commenter")
    public List<Comment> getComments() {
        return this.comments;
    }
    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

}

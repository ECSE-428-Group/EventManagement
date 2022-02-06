package com.group.eventmanagement.model;

import javax.persistence.*;
import java.util.*;
import java.sql.Timestamp;

@Entity
@Table(name = "events")
public class Event
{

    private Long eventId;
    @Id
    @GeneratedValue
    public Long getEventId() { return eventId; }
    public void setEventId(Long eventId) { this.eventId = eventId; }

    ///////////////////////////////////////////////////////////////////////////

    private Timestamp date;
    public Timestamp getDate() { return this.date; }
    public void setDate(Timestamp date) { this.date = date; }

    ///////////////////////////////////////////////////////////////////////////

    private boolean isPrivate;
    public boolean getIsPrivate() { return this.isPrivate; }
    public void setIsPrivate(boolean isPrivate) { this.isPrivate = isPrivate; }

    ///////////////////////////////////////////////////////////////////////////

    private boolean isVirtual;
    public boolean getIsVirtual() { return this.isVirtual; }
    public void setIsVirtual(boolean isVirtual) { this.isVirtual = isVirtual; }

    ///////////////////////////////////////////////////////////////////////////

    private String location;
    public String getLocation() { return this.location; }
    public void setLocation(String location) { this.location = location; }

    ///////////////////////////////////////////////////////////////////////////

    private String description;
    public String getDescription() { return this.description; }
    public void setDescription(String description) { this.description = description; }

    ///////////////////////////////////////////////////////////////////////////

    private String image;
    public String getImage() { return this.image; }
    public void setImage(String image) { this.image = image; }

    ///////////////////////////////////////////////////////////////////////////

    private List<Tag> tags;
    @ManyToMany(mappedBy = "events")
    public List<Tag> getTags() { return this.tags; }
    public void setTags(List<Tag> tags) { this.tags = tags; }

    ///////////////////////////////////////////////////////////////////////////

    private List<User> organizers;
    @ManyToMany
    public List<User> getOrganizers() { return this.organizers; }
    public void setOrganizers(List<User> organizers) { this.organizers = organizers; }

    ///////////////////////////////////////////////////////////////////////////

    private List<User> attendees;
    @ManyToMany
    public List<User> getAttendees() { return this.attendees; }
    public void setAttendees(List<User> attendees) { this.attendees = attendees; }

    ///////////////////////////////////////////////////////////////////////////

    private List<Post> posts;
    @OneToMany(targetEntity = Post.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "event")
    public List<Post> getPosts() { return this.posts; }
    public void setPosts(List<Post> posts) { this.posts = posts; }

}

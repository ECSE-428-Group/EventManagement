package com.group.eventmanagement.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Tag
{

    private String name;
    @Id
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
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

    private List<User> users;
    @ManyToMany(mappedBy = "tags")
    public List<User> getUsers() {
        return this.users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }

    ///////////////////////////////////////////////////////////////////////////

    private List<Event> events;
    @ManyToMany
    public List<Event> getEvents() { return this.events; }
    public void setEvents(List<Event> events) { this.events = events; }


}
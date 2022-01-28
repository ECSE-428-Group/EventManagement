package com.group.eventmanagement.model;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Id;

@Entity
public class RegistrationManager{

    private int id;

    @Id
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private Set<Person> persons;

    @OneToMany(cascade={CascadeType.ALL})
    public Set<Person> getPersons() {
        return this.persons;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }

    private Set<Registration> registrations;

    @OneToMany(cascade={CascadeType.ALL})
    public Set<Registration> getRegistrations() {
        return this.registrations;
    }

    public void setRegistrations(Set<Registration> registrations) {
        this.registrations = registrations;
    }

    private Set<Event> events;

    @OneToMany(cascade={CascadeType.ALL})
    public Set<Event> getEvents() {
        return this.events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

}
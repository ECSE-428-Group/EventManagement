package com.group.eventmanagement.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Admin extends Person
{

    @Id
    public String getUsername() { return super.getUsername(); }
    public void setUsername(String username) { super.setUsername(username); }

}
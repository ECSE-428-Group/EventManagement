package com.group.eventmanagement.model;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Person {

    private String username;
    @Id
    public String getUsername() { return this.username; }
    public void setUsername(String username) {
        this.username = username;
    }

    ///////////////////////////////////////////////////////////////////////////

    private String firstName;
    public String getFirstName() {
        return this.firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    ///////////////////////////////////////////////////////////////////////////

    private String lastName;
    public String getLastName() {
        return this.lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    ///////////////////////////////////////////////////////////////////////////
    
    private String email;
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    ///////////////////////////////////////////////////////////////////////////

    private String password;
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}

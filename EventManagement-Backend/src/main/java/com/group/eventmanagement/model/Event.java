package com.group.eventmanagement.model;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date; // B/c db uses sql data and time format and not the Java Date-Time format
import java.sql.Time;

@Entity
public class Event{

    private String name;

    @Id // primary key - placed on get method b/c get method gets invoked on primary key
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Date date;

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private Time startTime;

    public Time getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    private Time endTime;

    public Time getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

}
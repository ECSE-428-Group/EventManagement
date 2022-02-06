package com.group.eventmanagement.repository;


import java.util.List;

import com.group.eventmanagement.model.Event;

import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository <Event, Long> {

    Event findByEventId (Long eventId);
    Boolean existsByEventId (Long eventId);
    List<Event> findAll ();

}

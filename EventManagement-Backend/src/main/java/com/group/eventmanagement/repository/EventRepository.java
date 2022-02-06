package com.group.eventmanagement.repository;

import java.sql.Timestamp;
import java.util.List;

import com.group.eventmanagement.model.Event;
import com.group.eventmanagement.model.Tag;

import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository <Event, Long> {

    Event findByEventId (Long eventId);
    List<Event> getByDate (Timestamp date);
    List<Event> getByIsVirtual (Boolean isVirtual);
    List<Event> getByTags (List<Tag> tags);

}

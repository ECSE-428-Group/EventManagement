package com.group.eventmanagement.persistence;


import com.group.eventmanagement.model.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public interface EventRepository extends CrudRepository<Event, String> {

    Event findEventByName(String name);

}
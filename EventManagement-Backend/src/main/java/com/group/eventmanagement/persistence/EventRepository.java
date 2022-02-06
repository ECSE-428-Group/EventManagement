package com.group.eventmanagement.persistence;


import com.group.eventmanagement.modelOld.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface EventRepository extends CrudRepository<Event, String> {

    Event findEventByName(String name);

}
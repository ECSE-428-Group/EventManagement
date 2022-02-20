package com.group.eventmanagement.service;


import java.sql.Timestamp;
import java.util.List;


import com.group.eventmanagement.model.Event;
import com.group.eventmanagement.model.User;
import com.group.eventmanagement.repository.EventRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class EventService {
    private EventRepository eventRepository;

	@Autowired
	public EventService(EventRepository eventRepo) {
		this.eventRepository = eventRepo;
	}

@Transactional
	public Event createEvent(Long eventID, Timestamp eventDate, boolean isPrivate, boolean isVirtual, String location, String description, String image) {

		String error = "";

		//Input validation
		if(eventDate == null) {	//An event must have a date
			error += "This event has no date associated to it. ";
		}
		if(eventDate != null && eventDate.before(new Timestamp(System.currentTimeMillis()))) {	//An event cannot be scheduled for a date in the past
			error += "This date has already passsed. ";
		}
		if(location == null || location.trim().length() <= 0) {	//An event's location cannot be empty
			error += "This event has no location associated to it. ";
		}
		if(description == null || description.trim().length() <= 0) {	//An event's description cannot be empty
			error += "This event has no description associated to it. ";
		}
		if(image == null || image.trim().length() <= 0) {	//An event must have an image associated with it
			error += "This event has no image associated to it. ";
		}

        System.out.println(error);
		error = error.trim();
		if(error.length() > 0) {
			throw new IllegalArgumentException(error);
		}

		//Event creation
		Event newEvent = new Event();
		newEvent.setEventId(eventID);
		newEvent.setDate(eventDate);
		newEvent.setIsPrivate(isPrivate);
		newEvent.setIsVirtual(isVirtual);
		newEvent.setLocation(location);
		newEvent.setDescription(description);
		newEvent.setImage(image);

		eventRepository.save(newEvent);

		return newEvent;
	}
    public Event getEventById(Long eventId){
        Event event = eventRepository.findByEventId(eventId);
        return event;

    }

    public void registerUserToEvent(Event event, User user){
        String error = "";
        if (event==null){
            error += "An event has to be specified";
        }
        if (user == null){
            error+= "A user has to be specified";
        }
        List<User> attendees = event.getAttendees();
        if (attendees.contains(user)){
            error += "User already registered to the event";
        }
        if (!error.isEmpty()){
            throw new IllegalArgumentException(error);
        }
        attendees.add(user);
        event.setAttendees(attendees);
        eventRepository.save(event);

    }
    public void unregisterUserToEvent(Event event, User user){
        String error = "";
        if (event==null){
            error += "An event has to be specified";
        }
        if (user == null){
            error+= "A user has to be specified";
        }
        List<User> attendees = event.getAttendees();
        if (!attendees.contains(user)){
            error += "User is already not registered to the event";
        }
        if (!error.isEmpty()){
            throw new IllegalArgumentException(error);
        }
        attendees.remove(user);
        event.setAttendees(attendees);
        eventRepository.save(event);

    }
}

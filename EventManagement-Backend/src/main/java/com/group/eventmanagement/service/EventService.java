package com.group.eventmanagement.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.group.eventmanagement.model.Event;
import com.group.eventmanagement.repository.EventRepository;

@Service
public class EventService {

	private EventRepository eventRepo;
	
	@Autowired
	public EventService(EventRepository eventRepo) {
		this.eventRepo = eventRepo;
	}
	
	///// EVENT CREATION /////	
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
		
		eventRepo.save(newEvent);
		
		return newEvent;
	}	
}

package com.group.eventmanagement.controller;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.group.eventmanagement.service.EventService;
import com.group.eventmanagement.model.Event;
import com.group.eventmanagement.model.Tag;
import com.group.eventmanagement.model.User;
import com.group.eventmanagement.model.Post;
import com.group.eventmanagement.repository.EventRepository;
import com.group.eventmanagement.repository.UserRepository;

@CrossOrigin(origins = "*")
@RestController
public class EventController {

	
	private EventService eventService;
	
	
	@Autowired
	public EventRepository eventRepo;
	
	@Autowired
	public EventController(EventService eventService) {
		this.eventService = eventService;
	}
	
	@PutMapping(value = "/event/{eventID}")
	public void addAttendee(@PathVariable ("eventID") Long id,
	    		@RequestParam String callerUsername,
	    		@RequestParam String attendeeUsername) throws IllegalArgumentException{
		eventService.addAttendee(callerUsername, id, attendeeUsername);
	}
	
	@GetMapping(value = "/event/{eventID}")
	public User getAttendee(@PathVariable ("eventID") Long id,
	    		@RequestParam String callerUsername,
	    		@RequestParam String attendeeUsername) throws IllegalArgumentException{
		User attendee = eventService.getAttendee(callerUsername, id, attendeeUsername);
		return attendee;
	}
	
	@GetMapping(value = "/event/{eventID}")
	public List<User> getAllAttendees(@PathVariable ("eventID") Long id,
	    		@RequestParam String callerUsername
	    		) throws IllegalArgumentException{
		List<User> attendees = eventService.getAllAttendees(callerUsername, id);
		return attendees;
	}
	
	@PutMapping(value = "/event/{eventID}")
	public void removeAttendee(@PathVariable ("eventID") Long id,
			@RequestParam String callerUsername, @RequestParam String attendeeUsername) throws IllegalArgumentException{
		 eventService.removeAttendeeFromEvent(callerUsername, id, attendeeUsername);		 
	 }
	 
	 @PutMapping(value = "/event/{eventID}")
	    public void removeAllAttendees(@PathVariable ("eventID") Long id,
	    		@RequestParam String callerUsername
	    		) throws IllegalArgumentException{
		 eventService.removeAllAttendeesFromEvent(callerUsername, id);		 
		 
	 }
	
	
}

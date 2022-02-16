package com.group.eventmanagement.controller;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.group.eventmanagement.service.EventService;
import com.group.eventmanagement.model.Event;
import com.group.eventmanagement.model.Tag;
import com.group.eventmanagement.model.User;
import com.group.eventmanagement.model.Post;

@CrossOrigin(origins = "*")
@RestController
public class EventController {

	private EventService eventService;
	
	@Autowired
	public EventController(EventService eventService) {
		this.eventService = eventService;		
	}
	
	/////////// CREATE EVENT ///////////
	
	@PostMapping(value = {
			"/event/{eventID}",
			"/event/{eventID}/"			
	})
	public Event createEvent(
			@PathVariable("eventID") Long eventID,
			@RequestParam(name = "eventDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate eventDate,
			@RequestParam(name = "isPrivate") boolean isPrivate,
			@RequestParam(name = "isVirtual") boolean isVirtual,
			@RequestParam(name = "location") String location,
			@RequestParam(name = "description") String description,
			@RequestParam(name = "image") String image,
			@RequestParam(name = "tags") List<Tag> tags,
			@RequestParam(name = "organizers") List<User> organizers,
			@RequestParam(name = "attendees") List<User> attendees,
			@RequestParam(name = "posts") List<Post> posts			
			)throws IllegalArgumentException{	
		Timestamp convertedEventDate = Timestamp.valueOf(eventDate.atStartOfDay());
		Event newEvent = eventService.createEvent(eventID, convertedEventDate, isPrivate, isVirtual, location, description, image, tags, organizers, attendees, posts);
		return newEvent;
	}
}

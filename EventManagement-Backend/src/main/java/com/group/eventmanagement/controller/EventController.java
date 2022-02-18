package com.group.eventmanagement.controller;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
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
			@RequestParam(name = "tags") List<String> tagNames,
			@RequestParam(name = "organizers") List<String> organizerNames,
			@RequestParam(name = "attendees") List<String> attendeeNames,
			@RequestParam(name = "posts") List<String> postsID			
			)throws IllegalArgumentException{	
		List<Tag> tags = new ArrayList<Tag>();
		for(String name : tagNames) {
			tags.add(eventService.getTag(name));
		}
		List<User> organizers = new ArrayList<User>();
		for(String name : organizerNames) {
			organizers.add(eventService.getUser(name));
		}
		List<User> attendees = new ArrayList<User>();
		for(String name : attendeeNames) {
			attendees.add(eventService.getUser(name));
		}
		List<Post> posts = new ArrayList<Post>();
		for(String name : postsID) {
			posts.add(eventService.getPost(name));
		}
		Timestamp convertedEventDate = Timestamp.valueOf(eventDate.atStartOfDay());
		Event newEvent = eventService.createEvent(eventID, convertedEventDate, isPrivate, isVirtual, location, description, image, tags, organizers, attendees, posts);
		return newEvent;
	}
}

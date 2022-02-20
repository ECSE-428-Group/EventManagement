package com.group.eventmanagement.controller;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.group.eventmanagement.service.EventService;
import com.group.eventmanagement.service.UserService;
import com.group.eventmanagement.model.Event;
import com.group.eventmanagement.model.User;

@CrossOrigin(origins = "*")
@RestController
public class EventController {

	private EventService eventService;
	private UserService userService;

	@Autowired
	public EventController(EventService eventService, UserService userService) {
		this.eventService = eventService;
		this.userService = userService;
	}

	/////////// CREATE EVENT ///////////
	@PostMapping(value = {
			"/event/{eventId}",
			"/event/{eventId}/"
	})
	public Event createEvent(
			@PathVariable("eventId") String eventId,
			@RequestParam(name = "date") String date,
			@RequestParam(name = "isPrivate") String isPrivate,
			@RequestParam(name = "isVirtual") String isVirtual,
			@RequestParam(name = "location") String location,
			@RequestParam(name = "description") String description,
			@RequestParam(name = "image") String image
			)throws IllegalArgumentException{

		Long eventIDl = Long.parseLong(eventId);
		boolean isPrivateb = Boolean.parseBoolean(isPrivate);
		boolean isVirtualb = Boolean.parseBoolean(isVirtual);
		Timestamp convertedEventDate = Timestamp.valueOf(date);
		Event newEvent = eventService.createEvent(eventIDl, convertedEventDate, isPrivateb, isVirtualb, location, description, image);
		return newEvent;
	}

	@PutMapping(value = {
		"/event/{eventId}/register/{username}",
		"/event/{eventId}/register/{username}/"
	})
	public Event registerUser(
		@PathVariable("eventId") String eventId,
		@PathVariable("username") String username
	){
		Event event = eventService.getEventById(Long.parseLong(eventId));
		User user = userService.getUserByUsername(username);
		return eventService.registerUserToEvent(event, user);
		 
	}
	@PutMapping(value = {
		"/event/{eventId}/unregister/{username}",
		"/event/{eventId}/unregister/{username}/"
	})
	public Event unregisterUser(
		@PathVariable("eventId") String eventId,
		@PathVariable("username") String username
	){
		Event event = eventService.getEventById(Long.parseLong(eventId));
		User user = userService.getUserByUsername(username);
		return eventService.unregisterUserToEvent(event, user);
		 
	}
}

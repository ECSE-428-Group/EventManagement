package com.group.eventmanagement.controller;

import java.sql.Timestamp;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.group.eventmanagement.service.EventService;
import com.group.eventmanagement.model.Event;

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
}

package com.group.eventmanagement.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.group.eventmanagement.model.Event;
import com.group.eventmanagement.model.Post;
import com.group.eventmanagement.model.Tag;
import com.group.eventmanagement.model.User;
import com.group.eventmanagement.repository.EventRepository;
import com.group.eventmanagement.repository.PostRepository;
import com.group.eventmanagement.repository.TagRepository;
import com.group.eventmanagement.repository.UserRepository;

@Service
public class EventService {

	private EventRepository eventRepo;
	private TagRepository tagRepo;
	private UserRepository userRepo;
	private PostRepository postRepo;
	
	
	@Autowired
	public EventService(EventRepository eventRepo) {
		this.eventRepo = eventRepo;
	}
	
	///// EVENT CREATION /////	
	@Transactional
	public Event createEvent(Long eventID, Timestamp eventDate, boolean isPrivate, boolean isVirtual, String location, String description,
							 String image, List<Tag> tags, List<User> organizers, List<User> attendees, List<Post> posts) {
		
		String error = "";
		
		//Input validation
		/*if(eventRepo.existsById(eventID)) {	//An event with this ID already exists
			error+= "This ID is already in use. ";
		}*/
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
		if(tags == null || tags.isEmpty()) {	//An event must have at least one tag
			error += "This event has no tags associated to it. ";
		}
		if(organizers == null || organizers.isEmpty()) {	//An event must have at least one organizers
			error += "This event has no organizers associated to it. ";
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
		newEvent.setTags(tags);
		newEvent.setOrganizers(organizers);
		newEvent.setAttendees(attendees);
		newEvent.setPosts(posts);	
		
		eventRepo.save(newEvent);
		
		return newEvent;
	}	
	
	///// GET TAG /////	
	@Transactional
	public Tag getTag(String name) {
		return tagRepo.findByName(name);
	}
	///// GET USER /////	
	@Transactional
	public User getUser(String name) {
		return userRepo.findUserByUsername(name);
	}
	///// GET USER /////	
	@Transactional
	public Post getPost(String postID) {
		long l=Long.parseLong(postID);  
		return postRepo.findByPostId(l);
	}
	
}

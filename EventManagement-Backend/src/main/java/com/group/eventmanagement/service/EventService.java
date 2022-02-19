package com.group.eventmanagement.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.group.eventmanagement.model.Event;
import com.group.eventmanagement.model.User;
import com.group.eventmanagement.repository.EventRepository;
import com.group.eventmanagement.repository.UserRepository;

@Service
public class EventService {
	
	private EventRepository eventRepository;
	private UserRepository userRepository;

	@Autowired
    public EventService(EventRepository eventRepository,UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
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
		
		eventRepository.save(newEvent);
		
		return newEvent;
	}	
	
	
	//Adds an attendee to the event
	@Transactional
    public void addAttendee(String callerUsername, Long eventID, String attendeeUsername){
	
		 //store variables
        Event event = eventRepository.findByEventId(eventID);
        User user = userRepository.findUserByUsername(attendeeUsername);
        
      //checks if the event exists
        if( eventRepository.existsByEventId(eventID) == false){
            throw new IllegalArgumentException("The event you are trying to access does not exist.");
        }
        
        List<User> attendees = event.getAttendees(); //stores all attendees
        List<User> organizers = event.getOrganizers(); //stores all organizers
        ArrayList<String> organizerUsernames = new ArrayList<>(); //stores all organizer names
        ArrayList<String> attendeesUsernames = new ArrayList<>(); //stores all attendees names

        //list of organizers id
        for(User curr : organizers){
            String currUsername = curr.getUsername();
            organizerUsernames.add(currUsername);
        }
        
        //list of attendee id
        for(User curr : attendees){
            String currUsername = curr.getUsername();
            attendeesUsernames.add(currUsername);
        }

        //checks if the one calling the method is an organizer
        if(!organizerUsernames.contains(callerUsername)){
            throw new IllegalArgumentException("Must be an organizer to add an attendee to the event.");
        }
        
        //checks if the attendee exists
        if(!userRepository.existsByUsername(attendeeUsername)){
            throw new IllegalArgumentException("This attendee does not exist.");
        }
        //checks if the attendee aalready in event
        if(attendeesUsernames.contains(attendeeUsername)){
            throw new IllegalArgumentException("This attendee is already participating in this event.");
        }
        
        //updates list of attendees
        attendees.add(user);
        event.setAttendees(attendees);
	}
	
	//Return
    @Transactional
    public User getAttendee(String callerUsername, Long eventID, String attendeeUsername){

        //store variables
        Event event = eventRepository.findByEventId(eventID);
        
      //checks if the event exists
        if( eventRepository.existsByEventId(eventID) == false){
            throw new IllegalArgumentException("The event you are trying to access does not exist.");
        }
        
        List<User> attendees = event.getAttendees(); //stores all attendees
        List<User> organizers = event.getOrganizers(); //stores all organizers
        ArrayList<String> organizerUsernames = new ArrayList<>(); //stores all organizer names
        ArrayList<String> attendeesUsernames = new ArrayList<>(); //stores all attendees names

        //list of organizers id
        for(User curr : organizers){
            String currUsername = curr.getUsername();
            organizerUsernames.add(currUsername);
        }
        //list of attendee id
        for(User curr : attendees){
            String currUsername = curr.getUsername();
            attendeesUsernames.add(currUsername);
        }

        //checks if the one calling the method is an organizer
        if(!organizerUsernames.contains(callerUsername)){
            throw new IllegalArgumentException("Must be an organizer to view the list of attendees.");
        }
        
        //checks if the attendee exists
        if(!attendeesUsernames.contains(attendeeUsername)){
            throw new IllegalArgumentException("The attendee does not exist or is not subscribed to this event.");
        }

        //looks for correct attendee and returns it
        User attendee = userRepository.findUserByUsername(attendeeUsername);
        return attendee;
    }

    @Transactional
    public List<User> getAllAttendees(String callerUsername, Long eventID) {

        //store variables
        Event event = eventRepository.findByEventId(eventID);
        //checks if the event exists
        if( eventRepository.existsByEventId(eventID) == false){
            throw new IllegalArgumentException("The event you are trying to access does not exist.");
        }
        
        List<User> organizers = event.getOrganizers();
        ArrayList<String> organizerUsernames = new ArrayList<>();
        List<User> attendees = event.getAttendees();

        //list of organizers id
        for(User curr : organizers){
            String currUsername = curr.getUsername();
            organizerUsernames.add(currUsername);
        }

        //checks if the one calling the method is an organizer
        if(!organizerUsernames.contains(callerUsername)){
            throw new IllegalArgumentException("Must be an organizer to view the list of attendees.");
        }
      
        //checks if there are attendees
        if( attendees.size() == 0){
            throw new IllegalArgumentException("This event does not have any attendees.");
        }

        return attendees;
    }

    @Transactional
    public void removeAttendeeFromEvent(String callerUsername, Long eventID, String attendeeUsername){
        //store variables
        Event event = eventRepository.findByEventId(eventID);
        
        //checks if the event exists
        if( eventRepository.existsByEventId(eventID) == false){
            throw new IllegalArgumentException("The event you are trying to access does not exist.");
        }
        
        List<User> attendees = event.getAttendees();
        List<User> organizers = event.getOrganizers();
        ArrayList<String> organizerUsernames = new ArrayList<>();
        ArrayList<String> attendeesUsernames = new ArrayList<>(); //stores all attendees names
        
        //list of organizers id
        for(User curr : organizers){
            String currUsername = curr.getUsername();
            organizerUsernames.add(currUsername);
        }
        //list of attendee id
        for(User curr : attendees){
            String currUsername = curr.getUsername();
            attendeesUsernames.add(currUsername);
        }

        //checks if the one calling the method is an organizer
        if(!organizerUsernames.contains(callerUsername)){
            throw new IllegalArgumentException("Must be an organizer to remove an attendee.");
        }
        
        //checks if attempt to remove organizer
        if(organizerUsernames.contains(attendeeUsername)){
            throw new IllegalArgumentException("You cannot remove an organizer of the event.");
        }
        
        //checks if the attendee exists
        if(!attendeesUsernames.contains(attendeeUsername)){
            throw new IllegalArgumentException("The attendee does not exist or is not subscribed to this event.");
        }

        //Identify which object to remove
        User toRemove = userRepository.findUserByUsername(attendeeUsername);

        //if no errors call crud delete
        attendees.remove(toRemove);
        event.setAttendees(attendees);
        eventRepository.save(event);
    }

    @Transactional
    public void removeAllAttendeesFromEvent(String callerUsername, Long eventID) {
        //store variables
        Event event = eventRepository.findByEventId(eventID);
        
        //checks if the event exists
        if( eventRepository.existsByEventId(eventID) == false){
            throw new IllegalArgumentException("The event you are trying to access does not exist.");
        }
        
        List<User> organizers = event.getOrganizers();
        ArrayList<String> organizerUsernames = new ArrayList<>();
        List<User> attendees = event.getAttendees();

        //list of organizers id
        for(User curr : organizers){
            String currUsername = curr.getUsername();
            organizerUsernames.add(currUsername);
        }

        //checks if the one calling the method is an organizer
        if(!organizerUsernames.contains(callerUsername)){
            throw new IllegalArgumentException("Must be an organizer to remove attendees.");
        }
      
        //checks if there are attendees
        if( attendees.size() == 0 || attendees == null){
            throw new IllegalArgumentException("This event does not have any attendees.");
        }

        //crud call remove all attendees from that event
        attendees = null;
        event.setAttendees(attendees);
        eventRepository.save(event);
    }


}

package com.group.eventmanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.group.eventmanagement.model.User;

import com.group.eventmanagement.model.Event;
import com.group.eventmanagement.repository.EventRepository;

@Service
public class AttendeesService {
	 
	private EventRepository eventRepository;

    @Autowired
    public AttendeesService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

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
        User attendee = null;
        for(User current : attendees) {
        	if(current.getUsername() == attendeeUsername) {
        		attendee = current;
        	}
        }
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
        User toRemove = null;
        
        for(User current : attendees){
            String currentUsername = current.getUsername();
            if(currentUsername == attendeeUsername) {
            	toRemove = current;
            }
        }
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
        if( attendees == null){
            throw new IllegalArgumentException("This event does not have any attendees.");
        }

        //crud call remove all attendees from that event
        attendees = null;
        event.setAttendees(attendees);
        eventRepository.save(event);
    }

}

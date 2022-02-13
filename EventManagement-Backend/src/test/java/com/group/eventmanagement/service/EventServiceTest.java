package com.group.eventmanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.lenient;
import static org.mockito.ArgumentMatchers.anyLong;
import org.mockito.invocation.InvocationOnMock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.*;

import com.group.eventmanagement.model.User;
import com.group.eventmanagement.model.Event;
import com.group.eventmanagement.repository.EventRepository;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {
	
	@Mock
	private EventRepository eventRepository;

	@InjectMocks
	private EventService service;
	
	//Test values
	private static final String location = "Mugiwara Library";
	private static final Long eventID = (long) 12345;
	private static final boolean isPrivate = false;
	private static final boolean isVirtual = false;
	private static final String description = "This event will be fun.";
	private static final String image = "This is an image.";
	private static final Timestamp date = new Timestamp(System.currentTimeMillis());
	
	//Users of the system - 1,2 are attendees and 3 is an organizer
	private static final User user1 = new User();
	private static final User user2 = new User();
	private static final User user3 = new User();
	private static final User user4 = new User();
	private static String user1Username = "user1";
	private static String user3Username = "user3";
	private static String user4Username = "user4";
	
	
	@BeforeEach
	public void setMockOutput() {
	    lenient().when((eventRepository.findByEventId(anyLong()))).thenAnswer((InvocationOnMock invocation) -> {
	    	if (invocation.getArgument(0).equals(eventID)) {
	    		Event event = new Event();
				
				List<User> organizers = new ArrayList<>();
				List<User> attendees = new ArrayList<>();
				user1.setUsername(user1Username);
				user2.setUsername(TestData.userUsername);
				user3.setUsername(user3Username);
				user4.setUsername(user4Username);
				
				//User1 and User2 are attendees of the event
				attendees.add(user1);
				attendees.add(user2);
				//User3 and User4 are organizers of the event
				organizers.add(user3);
				organizers.add(user4);
				
				event.setDescription(description);
				event.setImage(image);
				event.setDate(date);
				event.setIsPrivate(isPrivate);
				event.setIsVirtual(isVirtual);
				event.setLocation(location);
				event.setEventId(eventID);
				event.setAttendees(attendees);
				event.setOrganizers(organizers);
				
				return event;
	    	}else {
	    		return null;
	    	}
	    		
	    });
	    
	    lenient().when(eventRepository.existsByEventId(anyLong())).thenAnswer((InvocationOnMock invocation) -> {
			
	    	if (invocation.getArgument(0).equals(eventID)) {	
			return true;
	    	}
	    	else{
	    		return false;
	    	}
			
		});
	}
	
	//----------------------GET ONE ATTENDEE TESTS---------------
	//Reminder: Attendees:1,2 Organizers: 3,4
	
	//Successful attempt test
	@Test
	public void getAttendeeSuccess() {
		
		try {
			service.getAttendee(user3Username, eventID, user1Username);
		} catch (IllegalArgumentException e) {
            fail();
		}
	}
	
	//tests if an attendee tries to access another attendee
	@Test
	public void getAttendeeNotOrganizer() {
		String error = "";
		
		try {
			service.getAttendee(user1Username, eventID, user3Username);
		} catch (IllegalArgumentException e) {
          error = e.getMessage();
		}
		 assertEquals("Must be an organizer to view the list of attendees.", error);
	}
	
	//tests if trying to get an attendee that doesn't exist/isn't in the event
	@Test
	public void getAttendeeDoesntExist() {
		String error = "";
		
		try {
			service.getAttendee(user3Username, eventID, "randomNameThatDoesntExist");
		} catch (IllegalArgumentException e) {
          error = e.getMessage();
		}
		 assertEquals("The attendee does not exist or is not subscribed to this event.", error);
		
	}
	//tests if trying to get an attendee that doesn't exist/isn't in the event
	@Test
	public void getAttendeeEventDoesntExist() {
		String error = "";
		Long randomID = (long) 999;
			
		try {
			service.getAttendee(user3Username, randomID, user1Username);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
			}
		 assertEquals("The event you are trying to access does not exist.", error);
			
	}
	
	//----------------------GET ALL ATTENDEES TESTS---------------
	//Reminder: Attendees:1,2 Organizers: 3,4
	
	//Successful attempt test
	@Test
	public void getAllAttendeesSuccess() {
		
		try {
			service.getAllAttendees(user3Username, eventID);
		} catch (IllegalArgumentException e) {
	           fail();
		}
	}
		
	//tests if an unauthorized user tries to view another attendee
	@Test
	public void getAllAttendeesNotOrganizer() {
		String error = "";
			
		try {
			service.getAllAttendees(user1Username, eventID);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("Must be an organizer to view the list of attendees.", error);
	}
		
	//tests if the event whose attendees we're trying to acess exists
	@Test
	public void getAllAttendeesEventDoesntExist() {
		String error = "";
		Long randomID = (long) 999;
		
		try {
			service.getAllAttendees(user3Username, randomID);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("The event you are trying to access does not exist.", error);
	}
	//Not sure how to empty attendee list because fill in beforeEach
	/*//tests if the event has attendees
	@Test
	public void getAllAttendeesNoAttendees() {
		String error = "";

		try {
			service.getAllAttendees(user3Username, eventID);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals("This event does not have any attendees.", error);
	}*/
	
	//----------------------REMOVE ONE ATTENDEE TESTS---------------
	//Reminder: Attendees:1,2 Organizers: 3,4
	
	//Successful attempt test
	@Test
	public void removeAttendeeSuccess() {
		
		try {
			service.removeAttendeeFromEvent(user3Username, eventID, user1Username);
		} catch (IllegalArgumentException e) {
	           fail();
		}
	}
	
	//tests if an unauthorized user calls it
	@Test
	public void removeAttendeeNotOrganizer() {
		String error = "";
		
		try {
			service.removeAttendeeFromEvent(TestData.existentUsername, eventID, user1Username);
		} catch (IllegalArgumentException e) {
			 error = e.getMessage();
		}
		 assertEquals("Must be an organizer to remove an attendee.", error);
	}
	
	//tests if the person that is being removed is an organizer
	@Test
	public void removeAttendeeTryToRemoveOrganizer() {
		String error = "";
		
		try {
			service.removeAttendeeFromEvent(user3Username, eventID, user4Username);
		} catch (IllegalArgumentException e) {
			 error = e.getMessage();
		}
		 assertEquals("You cannot remove an organizer of the event.", error);
	}
	//test if the event in question exists
	@Test
	public void removeAttendeeEventDoestExist() {
		String error = "";
		Long randomID = (long) 999;
		
		try {
			service.removeAttendeeFromEvent(user3Username, randomID, user1Username);
		} catch (IllegalArgumentException e) {
			 error = e.getMessage();
		}
		 assertEquals("The event you are trying to access does not exist.", error);
	}
	
	//tests attempt to remove an attendee that isn't there
	@Test
	public void removeAttendeeNotInEvent() {
		String error = "";
		
		try {
			service.removeAttendeeFromEvent(user3Username, eventID, "randomUsername");
		} catch (IllegalArgumentException e) {
			 error = e.getMessage();
		}
		 assertEquals("The attendee does not exist or is not subscribed to this event.", error);
	}
	
	//----------------------REMOVE ALL ATTENDEES TESTS---------------
	//Reminder: Attendees:1,2 Organizers: 3,4
		
	//Successful attempt test
	@Test
	public void removeAllAttendeesSuccess() {
		
		try {
			service.removeAllAttendeesFromEvent(user3Username, eventID);
		} catch (IllegalArgumentException e) {
	           fail();
		}
	}
	
	//tests if unauthorized user tries to remove all attendees
	@Test
	public void removeAllAttendeesNotOrganizer() {
		String error = "";
		
		try {
			service.removeAllAttendeesFromEvent(user1Username, eventID);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		 assertEquals("Must be an organizer to remove attendees.", error);
	}
	
	//tests if event that is accessed exists
	@Test
	public void removeAllAttendeesEventDoesntExist() {
		String error = "";
		Long randomID = (long) 999;
		
		try {
			service.removeAllAttendeesFromEvent(user3Username, randomID);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		 assertEquals("The event you are trying to access does not exist.", error);
	}
	
	//tests if there are any attendees to remove
	/*@Test
	public void removeAllAttendeesNoAttendees() {
		String error = "";
		
		try {
			service.removeAllAttendeesFromEvent(user3Username, eventID);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		 assertEquals("This event does not have any attendees.", error);
	}*/
	

}

package com.group.eventmanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.lenient;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
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
import com.group.eventmanagement.model.Tag;
import com.group.eventmanagement.model.Event;
import com.group.eventmanagement.repository.EventRepository;
import com.group.eventmanagement.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {

	@Mock
	private EventRepository eventRepository;

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private EventService service;

	//Test values
	private static final String location = "This is the location.";
	private static final Long eventID = (long) 12345;
	private static final Long eventNoAttendeesID = (long) 5555;
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
	private static final User user5 = new User();
	private static String user1Username = "user1";
	private static String user3Username = "user3";
	private static String user4Username = "user4";
	private static String user5Username = "user5";


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
	    	}
	    	if (invocation.getArgument(0).equals(eventNoAttendeesID)) {
	    		Event event = new Event();

				List<User> organizers = new ArrayList<>();
				List<User> attendees = new ArrayList<>();
				user1.setUsername(user1Username);
				user2.setUsername(TestData.userUsername);
				user3.setUsername(user3Username);
				user4.setUsername(user4Username);

				//User1 and User2 are attendees of the event
				/*attendees.add(user1);
				attendees.add(user2);*/
				//User3 and User4 are organizers of the event
				organizers.add(user3);
				organizers.add(user4);

				event.setDescription(description);
				event.setImage(image);
				event.setDate(date);
				event.setIsPrivate(isPrivate);
				event.setIsVirtual(isVirtual);
				event.setLocation(location);
				event.setEventId(eventNoAttendeesID);
				event.setAttendees(attendees);
				event.setOrganizers(organizers);

				return event;
	    	}
	    	else {

	    		return null;
	    	}

	    });

	    lenient().when(eventRepository.existsByEventId(anyLong())).thenAnswer((InvocationOnMock invocation) -> {

	    	if (invocation.getArgument(0).equals(eventID) || invocation.getArgument(0).equals(eventNoAttendeesID)) {
			return true;
	    	}
	    	else{
	    		return false;
	    	}

		});
	    lenient().when(userRepository.findUserByUsername(anyString())).thenAnswer((InvocationOnMock invocation) -> {

	    	if (invocation.getArgument(0).equals(TestData.existentUsername)) {
	    		User user = new User();
	    		user.setUsername(user5Username);
	    		return user;
	    	}
	    	else{
	    		return null;
	    	}

		});
	    lenient().when(userRepository.existsByUsername(anyString())).thenAnswer((InvocationOnMock invocation) -> {

	    	if (invocation.getArgument(0).equals(user5Username) || invocation.getArgument(0).equals(user1Username)){
			return true;
	    	}
	    	else{
	    		return false;
	    	}

		});
	}
	//----------------------ADD ATTENDEE TESTS---------------
		//Reminder: Attendees:1,2,5 Organizers: 3,4

		//Successful attempt test
		@Test
		public void addAttendeeSuccess() {

			try {
				service.addAttendee(user3Username, eventID, user5Username);
			} catch (IllegalArgumentException e) {
	            fail();
			}
		}

		//tests if an attendee tries to add another attendee
		@Test
		public void addAttendeeNotOrganizer() {
			String error = "";

			try {
				service.addAttendee(user1Username, eventID, user2.getUsername());
			} catch (IllegalArgumentException e) {
	          error = e.getMessage();
			}
			 assertEquals("Must be an organizer to add an attendee to the event.", error);
		}

		//tests if trying to add an attendee that doesn't exist/isn't in the event
		@Test
		public void addAttendeeDoesntExist() {
			String error = "";

			try {
				service.addAttendee(user3Username, eventID, "randomNameThatDoesntExist");
			} catch (IllegalArgumentException e) {
	          error = e.getMessage();
			}
			 assertEquals("This attendee does not exist.", error);
		}
		//tests if trying to add an attendee that doesn't exist/isn't in the event
				@Test
				public void addAttendeeAlreadyThere() {
					String error = "";

					try {
						service.addAttendee(user3Username, eventID, user1Username);
					} catch (IllegalArgumentException e) {
			          error = e.getMessage();
					}
					 assertEquals("This attendee is already participating in this event.", error);
				}

		//tests if trying to add an attendee that doesn't exist/isn't in the event
		@Test
		public void addAttendeeEventDoesntExist() {
			String error = "";
			Long randomID = (long) 999;

			try {
				service.addAttendee(user3Username, randomID, user1Username);
			} catch (IllegalArgumentException e) {
				error = e.getMessage();
				}
			 assertEquals("The event you are trying to access does not exist.", error);

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
	//tests if the event has attendees
	@Test
	public void getAllAttendeesNoAttendees() {
		String error = "";

		try {
			service.getAllAttendees(user3Username, eventNoAttendeesID);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("This event does not have any attendees.", error);
	}

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
	@Test
	public void removeAllAttendeesNoAttendees() {
		String error = "";

		try {
			service.removeAllAttendeesFromEvent(user3Username, eventNoAttendeesID);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		 assertEquals("This event does not have any attendees.", error);
	}



	/////////// EVENT CREATION TESTS ///////////
	@Test
	//Creation of a valid event
	public void createValidEvent() {
		List<User> orgs = new ArrayList<User>();
		List<Tag> tags = new ArrayList<Tag>();
		Tag tag1 = new Tag();
		User org1 = new User();
		orgs.add(org1);
		tags.add(tag1);

		Event newEvent = null;
		try {
			newEvent = service.createEvent(TestData.eventID, TestData.eventDate, TestData.isPrivate,
					TestData.isVirtual, TestData.eventLocation, TestData.eventDescription, TestData.eventImage);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNotNull(newEvent);
		assertEquals(TestData.eventID, newEvent.getEventId());
		assertEquals(TestData.eventDate, newEvent.getDate());
		assertEquals(TestData.isPrivate, newEvent.getIsPrivate());
		assertEquals(TestData.isVirtual, newEvent.getIsVirtual());
		assertEquals(TestData.eventLocation, newEvent.getLocation());
		assertEquals(TestData.eventDescription, newEvent.getDescription());
		assertEquals(TestData.eventImage, newEvent.getImage());
	}

	@Test
	//An event must have a date
	public void createEventWithNoDate() {
		Event eventWithNullDate = null;
		String er = "";
		try {
			eventWithNullDate = service.createEvent(TestData.eventID, null, TestData.isPrivate,
					TestData.isVirtual, TestData.eventLocation, TestData.eventDescription, TestData.eventImage);
		} catch (IllegalArgumentException e) {
			er = e.getMessage();
		}
		assertNull(eventWithNullDate);
		assertTrue(er.contains("This event has no date associated to it."));
	}

	@Test
	//An event cannot be scheduled for a date in the past
	public void createEventWithInvalidDate() {
		Event eventWithInvalidDate = null;
		String er = "";
		try {
			eventWithInvalidDate = service.createEvent(TestData.eventID, TestData.invalidEventDate, TestData.isPrivate,
					TestData.isVirtual, TestData.eventLocation, TestData.eventDescription, TestData.eventImage);
		} catch (IllegalArgumentException e) {
			er = e.getMessage();
		}
		assertNull(eventWithInvalidDate);
		assertTrue(er.contains("This date has already passsed."));
	}

	@Test
	//An event's location cannot be empty (null)
	public void createEventWithNullLocation() {
		Event eventWithNoLocation = null;
		String er = "";
		try {
			eventWithNoLocation = service.createEvent(TestData.eventID, TestData.eventDate, TestData.isPrivate,
					TestData.isVirtual, null, TestData.eventDescription, TestData.eventImage);
		} catch (IllegalArgumentException e) {
			er = e.getMessage();
		}
		assertNull(eventWithNoLocation);
		assertTrue(er.contains("This event has no location associated to it."));
	}
	@Test
	//An event's location cannot be empty (empty string)
	public void createEventWithNoLocation() {
		Event eventWithNoLocation = null;
		String er = "";
		try {
			eventWithNoLocation = service.createEvent(TestData.eventID, TestData.eventDate, TestData.isPrivate,
					TestData.isVirtual, "", TestData.eventDescription, TestData.eventImage);
		} catch (IllegalArgumentException e) {
			er = e.getMessage();
		}
		assertNull(eventWithNoLocation);
		assertTrue(er.contains("This event has no location associated to it."));
	}

	@Test
	//An event's description cannot be empty (null)
	public void createEventWithNullDescription() {
		Event eventWithNullDescription = null;
		String er = "";
		try {
			eventWithNullDescription = service.createEvent(TestData.eventID, TestData.eventDate, TestData.isPrivate,
					TestData.isVirtual, TestData.eventLocation, null, TestData.eventImage);
		} catch (IllegalArgumentException e) {
			er = e.getMessage();
		}
		assertNull(eventWithNullDescription);
		assertTrue(er.contains("This event has no description associated to it."));
	}


	@Test
	//An event's description cannot be empty (empty string)
	public void createEventWithNoDescription() {
		Event eventWithNoDescription = null;
		String er = "";
		try {
			eventWithNoDescription = service.createEvent(TestData.eventID, TestData.eventDate, TestData.isPrivate,
					TestData.isVirtual, TestData.eventLocation, "", TestData.eventImage);
		} catch (IllegalArgumentException e) {
			er = e.getMessage();
		}
		assertNull(eventWithNoDescription);
		assertTrue(er.contains("This event has no description associated to it."));
	}

	@Test
	//An event must have an image associated with it (null)
	public void createEventWithNullImage() {
		Event eventWithNullImage = null;
		String er = "";
		try {
			eventWithNullImage = service.createEvent(TestData.eventID, TestData.eventDate, TestData.isPrivate,
					TestData.isVirtual, TestData.eventLocation, TestData.eventDescription, null);
		} catch (IllegalArgumentException e) {
			er = e.getMessage();
		}
		assertNull(eventWithNullImage);
		assertTrue(er.contains("This event has no image associated to it."));
	}

	@Test
	//An event must have an image associated with it (empty string)
	public void createEventWithNoImage() {
		Event eventWithNoImage = null;
		String er = "";
		try {
			eventWithNoImage = service.createEvent(TestData.eventID, TestData.eventDate, TestData.isPrivate,
					TestData.isVirtual, TestData.eventLocation, TestData.eventDescription, "");
		} catch (IllegalArgumentException e) {
			er = e.getMessage();
		}
		assertNull(eventWithNoImage);
		assertTrue(er.contains("This event has no image associated to it."));
	}
}

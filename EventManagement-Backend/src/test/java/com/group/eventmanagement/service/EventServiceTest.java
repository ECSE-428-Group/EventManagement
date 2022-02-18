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

	@InjectMocks
	private EventService eventService;
		
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
			newEvent = eventService.createEvent(TestData.eventID, TestData.eventDate, TestData.isPrivate,
					TestData.isVirtual, TestData.eventLocation, TestData.eventDescription, TestData.eventImage, tags,
					orgs, TestData.eventAttendees, TestData.eventPosts);
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
		assertEquals(tags, newEvent.getTags());
		assertEquals(orgs, newEvent.getOrganizers());
		assertEquals(TestData.eventAttendees, newEvent.getAttendees());
		assertEquals(TestData.eventPosts, newEvent.getPosts());
	}
	
	/*@Test
	//An event with this ID already exists
	public void createEventWithDuplicateID() {
		Event event1 = new Event();
		Event event2 = null;
		String er = "";		
		event1.setEventId(TestData.eventID);		
		try {
			event2 = eventService.createEvent(TestData.eventID, TestData.eventDate, TestData.isPrivate,
					TestData.isVirtual, TestData.eventLocation, TestData.eventDescription, TestData.eventImage, TestData.eventTags,
					TestData.eventOrganizers, TestData.eventAttendees, TestData.eventPosts);
		} catch (IllegalArgumentException e) {
			er = e.getMessage();
		}
		assertNotNull(event1);
		assertNull(event2);
		assertEquals("This ID is already in use. ",er);		
	}*/
	
	@Test
	//An event must have a date
	public void createEventWithNoDate() {
		Event eventWithNullDate = null;
		String er = "";
		try {
			eventWithNullDate = eventService.createEvent(TestData.eventID, null, TestData.isPrivate,
					TestData.isVirtual, TestData.eventLocation, TestData.eventDescription, TestData.eventImage, TestData.eventTags,
					TestData.eventOrganizers, TestData.eventAttendees, TestData.eventPosts);
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
			eventWithInvalidDate = eventService.createEvent(TestData.eventID, TestData.invalidEventDate, TestData.isPrivate,
					TestData.isVirtual, TestData.eventLocation, TestData.eventDescription, TestData.eventImage, TestData.eventTags,
					TestData.eventOrganizers, TestData.eventAttendees, TestData.eventPosts);
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
			eventWithNoLocation = eventService.createEvent(TestData.eventID, TestData.eventDate, TestData.isPrivate,
					TestData.isVirtual, null, TestData.eventDescription, TestData.eventImage, TestData.eventTags,
					TestData.eventOrganizers, TestData.eventAttendees, TestData.eventPosts);
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
			eventWithNoLocation = eventService.createEvent(TestData.eventID, TestData.eventDate, TestData.isPrivate,
					TestData.isVirtual, "", TestData.eventDescription, TestData.eventImage, TestData.eventTags,
					TestData.eventOrganizers, TestData.eventAttendees, TestData.eventPosts);
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
			eventWithNullDescription = eventService.createEvent(TestData.eventID, TestData.eventDate, TestData.isPrivate,
					TestData.isVirtual, TestData.eventLocation, null, TestData.eventImage, TestData.eventTags,
					TestData.eventOrganizers, TestData.eventAttendees, TestData.eventPosts);
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
			eventWithNoDescription = eventService.createEvent(TestData.eventID, TestData.eventDate, TestData.isPrivate,
					TestData.isVirtual, TestData.eventLocation, "", TestData.eventImage, TestData.eventTags,
					TestData.eventOrganizers, TestData.eventAttendees, TestData.eventPosts);
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
			eventWithNullImage = eventService.createEvent(TestData.eventID, TestData.eventDate, TestData.isPrivate,
					TestData.isVirtual, TestData.eventLocation, TestData.eventDescription, null, TestData.eventTags,
					TestData.eventOrganizers, TestData.eventAttendees, TestData.eventPosts);
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
			eventWithNoImage = eventService.createEvent(TestData.eventID, TestData.eventDate, TestData.isPrivate,
					TestData.isVirtual, TestData.eventLocation, TestData.eventDescription, "", TestData.eventTags,
					TestData.eventOrganizers, TestData.eventAttendees, TestData.eventPosts);
		} catch (IllegalArgumentException e) {
			er = e.getMessage();
		}
		assertNull(eventWithNoImage);
		assertTrue(er.contains("This event has no image associated to it."));
	}
	
	@Test
	//An event must have at least one tag
	public void createEventWithNullTags() {
		Event eventWithNullTags = null;
		List<Tag> tags = null;
		String er = "";
		try {
			eventWithNullTags = eventService.createEvent(TestData.eventID, TestData.eventDate, TestData.isPrivate,
					TestData.isVirtual, TestData.eventLocation, TestData.eventDescription, TestData.eventImage, tags,
					TestData.eventOrganizers, TestData.eventAttendees, TestData.eventPosts);
		} catch (IllegalArgumentException e) {
			er = e.getMessage();
		}
		assertNull(eventWithNullTags);
		assertTrue(er.contains("This event has no tags associated to it."));
	}
	
	@Test
	//An event must have at least one tag
	public void createEventWithNoTags() {
		Event eventWithNoTags = null;
		List<Tag> tags = new ArrayList<Tag>();
		String er = "";
		try {
			eventWithNoTags = eventService.createEvent(TestData.eventID, TestData.eventDate, TestData.isPrivate,
					TestData.isVirtual, TestData.eventLocation, TestData.eventDescription, TestData.eventImage, tags,
					TestData.eventOrganizers, TestData.eventAttendees, TestData.eventPosts);
		} catch (IllegalArgumentException e) {
			er = e.getMessage();
		}
		assertNull(eventWithNoTags);
		assertTrue(er.contains("This event has no tags associated to it."));
	}
	
	@Test
	//An event must have at least one organizers
	public void createEventWithNullOrganizers() {
		Event eventWithNullOrgs = null;
		List<User> orgs = null;
		String er = "";
		try {
			eventWithNullOrgs = eventService.createEvent(TestData.eventID, TestData.eventDate, TestData.isPrivate,
					TestData.isVirtual, TestData.eventLocation, TestData.eventDescription, TestData.eventImage, TestData.eventTags,
					orgs, TestData.eventAttendees, TestData.eventPosts);
		} catch (IllegalArgumentException e) {
			er = e.getMessage();
		}
		assertNull(eventWithNullOrgs);
		assertTrue(er.contains("This event has no organizers associated to it."));	
	}
	
	@Test
	//An event must have at least one organizers
	public void createEventWithNoOrganizers() {
		Event eventWithNoOrgs = null;
		List<User> orgs = new ArrayList<User>();
		String er = "";
		try {
			eventWithNoOrgs = eventService.createEvent(TestData.eventID, TestData.eventDate, TestData.isPrivate,
					TestData.isVirtual, TestData.eventLocation, TestData.eventDescription, TestData.eventImage, TestData.eventTags,
					orgs, TestData.eventAttendees, TestData.eventPosts);
		} catch (IllegalArgumentException e) {
			er = e.getMessage();
		}
		assertNull(eventWithNoOrgs);
		assertTrue(er.contains("This event has no organizers associated to it."));	
	}
}

package com.group.eventmanagement.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.group.eventmanagement.model.Event;
import com.group.eventmanagement.model.User;
import com.group.eventmanagement.service.EventService;

@WebMvcTest(EventController.class)
public class EventControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EventService eventService;

	//Tests if controller can validly add attendee
	@Test
	public void testValidAddAttendee() {
		//mockUp Event
		Event mockEvent = TestData.createEventObject(TestData.eventID, TestData.eventDate, TestData.isPrivate, TestData.isVirtual, TestData.eventLocation, TestData.eventDescription, TestData.eventImage);

		//create attendee
		User attendee = new User();
		attendee.setUsername(TestData.anotherUserUsername);
		TestData.eventAttendees.add(attendee);
		//create user
		User organizer = new User();
		organizer.setUsername(TestData.johnUsername);
		TestData.eventOrganizers.add(organizer);
		//set attendees and organizers
		mockEvent.setAttendees(TestData.eventAttendees);
		mockEvent.setOrganizers(TestData.eventOrganizers);

		try {
			this.mockMvc.perform(put("/event/addOne/"+TestData.eventID)
					.param("callerUsername", TestData.johnUsername)
					.param("attendeeUsername", TestData.anotherUserUsername)
					).andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Tests invalid request
	@Test
	public void testInvalidAddAttendee() {
		//mockUp Event
		Event mockEvent = TestData.createEventObject(TestData.eventID, TestData.eventDate, TestData.isPrivate, TestData.isVirtual, TestData.eventLocation, TestData.eventDescription, TestData.eventImage);

		//create attendee
		User attendee = new User();
		attendee.setUsername(TestData.anotherUserUsername);
		TestData.eventAttendees.add(attendee);
		//create user
		User organizer = new User();
		organizer.setUsername(TestData.johnUsername);
		TestData.eventOrganizers.add(organizer);
		//set attendees and organizers
		mockEvent.setAttendees(TestData.eventAttendees);
		mockEvent.setOrganizers(TestData.eventOrganizers);

		when(eventService.addAttendee(TestData.johnUsername, TestData.invalidEventID, TestData.anotherUserUsername))
		.thenThrow(IllegalArgumentException.class);

		try {
			this.mockMvc.perform(put("/event/addOne/"+TestData.invalidEventID)
					.param("callerUsername", TestData.johnUsername)
					.param("attendeeUsername", TestData.anotherUserUsername)
					).andExpect(status().isInternalServerError());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testValidGetAttendee() {
		//mockUp Event
		Event mockEvent = TestData.createEventObject(TestData.eventID, TestData.eventDate, TestData.isPrivate, TestData.isVirtual, TestData.eventLocation, TestData.eventDescription, TestData.eventImage);
			when(eventService.createEvent(TestData.eventDate, TestData.isPrivate, TestData.isVirtual, TestData.eventLocation, TestData.eventDescription, TestData.eventImage))
			.thenReturn(mockEvent);

			//create attendee
			User attendee = new User();
			attendee.setUsername(TestData.anotherUserUsername);
			TestData.eventAttendees.add(attendee);
			//create user
			User organizer = new User();
			organizer.setUsername(TestData.johnUsername);
			TestData.eventOrganizers.add(organizer);
			//set attendees and organizers
			mockEvent.setAttendees(TestData.eventAttendees);
			mockEvent.setOrganizers(TestData.eventOrganizers);


		try {
			this.mockMvc.perform(get("/event/getOne/"+TestData.eventID)
					.param("callerUsername", TestData.johnUsername)
					.param("attendeeUsername", TestData.anotherUserUsername)
					).andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInvalidGetAttendee() {
		//mockUp Event
		Event mockEvent = TestData.createEventObject(TestData.eventID, TestData.eventDate, TestData.isPrivate, TestData.isVirtual, TestData.eventLocation, TestData.eventDescription, TestData.eventImage);

		//create attendee
		User attendee = new User();
		attendee.setUsername(TestData.anotherUserUsername);
		TestData.eventAttendees.add(attendee);
		//create organizer
		User organizer = new User();
		organizer.setUsername(TestData.johnUsername);
		TestData.eventOrganizers.add(organizer);
		//set attendees and organizers
		mockEvent.setAttendees(TestData.eventAttendees);
		mockEvent.setOrganizers(TestData.eventOrganizers);

		when(eventService.getAttendee(TestData.johnUsername, TestData.invalidEventID, TestData.anotherUserUsername))
		.thenThrow(IllegalArgumentException.class);

		try {
			this.mockMvc.perform(get("/event/getOne/"+TestData.invalidEventID)
					.param("callerUsername", TestData.johnUsername)
					.param("attendeeUsername", TestData.anotherUserUsername)
					).andExpect(status().isInternalServerError());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testValidGetAllAttendees() {
		//mockUp Event
		Event mockEvent = TestData.createEventObject(TestData.eventID, TestData.eventDate, TestData.isPrivate, TestData.isVirtual, TestData.eventLocation, TestData.eventDescription, TestData.eventImage);

		//create attendee
		User attendee = new User();
		attendee.setUsername(TestData.anotherUserUsername);
		TestData.eventAttendees.add(attendee);
		//create user
		User organizer = new User();
		organizer.setUsername(TestData.johnUsername);
		TestData.eventOrganizers.add(organizer);
		//set attendees and organizers
		mockEvent.setAttendees(TestData.eventAttendees);
		mockEvent.setOrganizers(TestData.eventOrganizers);


		try {
			this.mockMvc.perform(get("/event/getAll/"+TestData.eventID)
					.param("callerUsername", TestData.johnUsername)
					).andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInvalidGetAllAttendees() {
		//mockUp Event
		Event mockEvent = TestData.createEventObject(TestData.eventID, TestData.eventDate, TestData.isPrivate, TestData.isVirtual, TestData.eventLocation, TestData.eventDescription, TestData.eventImage);

		//create attendee
		User attendee = new User();
		attendee.setUsername(TestData.anotherUserUsername);
		TestData.eventAttendees.add(attendee);
		//create user
		User organizer = new User();
		organizer.setUsername(TestData.johnUsername);
		TestData.eventOrganizers.add(organizer);
		//set attendees and organizers
		mockEvent.setAttendees(TestData.eventAttendees);
		mockEvent.setOrganizers(TestData.eventOrganizers);

		when(eventService.getAllAttendees(TestData.johnUsername, TestData.invalidEventID))
		.thenThrow(IllegalArgumentException.class);

		try {
			this.mockMvc.perform(get("/event/getAll/"+TestData.invalidEventID)
					.param("callerUsername", TestData.johnUsername)
					).andExpect(status().isInternalServerError());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testValidRemoveAttendee() {
		//mockUp Event
		Event mockEvent = TestData.createEventObject(TestData.eventID, TestData.eventDate, TestData.isPrivate, TestData.isVirtual, TestData.eventLocation, TestData.eventDescription, TestData.eventImage);

		//create attendee
		User attendee = new User();
		attendee.setUsername(TestData.anotherUserUsername);
		TestData.eventAttendees.add(attendee);
		//create user
		User organizer = new User();
		organizer.setUsername(TestData.johnUsername);
		TestData.eventOrganizers.add(organizer);
		//set attendees and organizers
		mockEvent.setAttendees(TestData.eventAttendees);
		mockEvent.setOrganizers(TestData.eventOrganizers);

		when(eventService.removeAttendeeFromEvent(TestData.johnUsername, mockEvent.getEventId(), TestData.anotherUserUsername))
		.thenThrow(IllegalArgumentException.class);

		try {
			this.mockMvc.perform(put("/event/removeOne/"+TestData.eventID)
					.param("callerUsername", TestData.johnUsername)
					.param("attendeeUsername", TestData.anotherUserUsername)
					).andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testEventCreation() {
		Event mockEvent = TestData.createEventObject(TestData.eventID, TestData.eventDate, TestData.isPrivate, TestData.isVirtual, TestData.eventLocation, TestData.eventDescription, TestData.eventImage);
		when(eventService.createEvent(TestData.eventDate, TestData.isPrivate, TestData.isVirtual, TestData.eventLocation, TestData.eventDescription, TestData.eventImage))
		.thenReturn(mockEvent);

		try {
			this.mockMvc.perform(post("/event/")
					.param("date", TestData.eventDate.toString())
					.param("isPrivate", String.valueOf(TestData.isPrivate))
					.param("isVirtual", String.valueOf(TestData.isVirtual))
					.param("location", TestData.eventLocation)
					.param("description", TestData.eventDescription)
					.param("image", TestData.eventImage)
					).andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInvalidRemoveAllAttendees() {
		//mockUp Event
		Event mockEvent = TestData.createEventObject(TestData.eventID, TestData.eventDate, TestData.isPrivate, TestData.isVirtual, TestData.eventLocation, TestData.eventDescription, TestData.eventImage);
			when(eventService.createEvent(TestData.eventDate, TestData.isPrivate, TestData.isVirtual, TestData.eventLocation, TestData.eventDescription, TestData.eventImage))
			.thenReturn(mockEvent);

			//create attendee
			User attendee = new User();
			attendee.setUsername(TestData.anotherUserUsername);
			TestData.eventAttendees.add(attendee);
			//create user
			User organizer = new User();
			organizer.setUsername(TestData.johnUsername);
			TestData.eventOrganizers.add(organizer);
			//set attendees and organizers
			mockEvent.setAttendees(TestData.eventAttendees);
			mockEvent.setOrganizers(TestData.eventOrganizers);


		when(eventService.removeAllAttendeesFromEvent(TestData.johnUsername, TestData.invalidEventID))
		.thenThrow(IllegalArgumentException.class);

		try {
			this.mockMvc.perform(put("/event/removeAll/"+TestData.invalidEventID)
					.param("callerUsername", TestData.johnUsername)
					).andExpect(status().isInternalServerError());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

	public void testInvalidEventCreation() {
		when(eventService.createEvent(TestData.invalidEventDate, TestData.isPrivate, TestData.isVirtual, TestData.eventLocation, TestData.eventDescription, TestData.eventImage))
		.thenThrow(IllegalArgumentException.class);

		try {
			this.mockMvc.perform(post("/event/"+TestData.eventID)
					.param("date", TestData.invalidEventDate.toString())
					.param("isPrivate", "false")
					.param("isVirtual", "false")
					.param("location", TestData.eventLocation)
					.param("description", TestData.eventDescription)
					.param("image", TestData.eventImage)
					).andExpect(status().isInternalServerError());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
